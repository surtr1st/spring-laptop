package com.ps17931.controller;

import com.ps17931.domain.Account;
import com.ps17931.domain.Authority;
import com.ps17931.domain.MailInfo;
import com.ps17931.service.AccountService;
import com.ps17931.service.AuthorityService;
import com.ps17931.service.MailerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private String emailCode = "";
	private String username = "";
	private final Random rand = new Random();
	private final AccountService service;
	private final AuthorityService authService;
	private final MailerService mailer;
	private final HttpServletRequest request;

	@Autowired
	private AccountController(AccountService service, AuthorityService authService, MailerService mailer, HttpServletRequest request) {
		this.service = service;
		this.authService = authService;
		this.mailer = mailer;
		this.request = request;
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("account") Account account) {
		return "security/register";
	}

	@PostMapping("/register")
	public String doRegister(@Valid @ModelAttribute("account") Account account, Errors errors) {
		if(errors.hasErrors())
			return register(account);

		service.createAccount(account);
		Authority auth = new Authority();
		auth.setAccount(account);
		auth.setRid("USER");
		authService.createAuthority(auth);

		return "security/success";
	}

	@GetMapping("/profile")
	public String toProfile() {
		return "redirect:/assets/js/private/profile.html";
	}


	@GetMapping("/forgot-password")
	public String toForgotPassword() {
		return "service/forgot-password";
	}

	@PostMapping("/forgot-password")
	public String sendMail(Model model) {
		String email = request.getParameter("email");
		Account account = service.getUserByEmail(email);
		username = account.getUsername();

		if (email.equals(account.getEmail())) {
			int number = rand.nextInt(999999);
			emailCode = String.valueOf(number);
			try {
				mailer.send(new MailInfo(email, "Your verify code is", emailCode));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "service/verify";
		} else
			model.addAttribute("msg", "Invalid Email!");

		return "service/forgot-password";
	}

	@PostMapping("/verify")
	public String verifyCode(Model model) {
		String code = request.getParameter("code");

		if (code.equals(emailCode))
			return "service/change-password";
		else
			model.addAttribute("msg", "Invalid Code!");

		return "service/verify";
	}

	@PostMapping("/change-new-password")
	public String changePassword(Model model) {
		String npw = request.getParameter("npw");
		String rpw = request.getParameter("rpw");

		String errors = "";

		if (npw.length() < 6) {
			errors += "/";
			model.addAttribute("newp", "Password must be 6 - 15 characters!");
		}

		if (rpw.matches(npw))
			model.addAttribute("pw", "");
		else {
			errors += "/";
			model.addAttribute("pw", "Repeat password doesn't match!");
		}

		if (errors.length() == 0) {
			model.addAttribute("msg", "Changed password successfully!");
			service.updatePassword(username, npw);
			return "service/success";
		}

		return "service/change-password";
	}
}
