package com.ps17931.service;

import com.ps17931.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ps17931.domain.Account;
import com.ps17931.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository repo;

    private final BCryptPasswordEncoder pe;

    @Autowired
    private AccountService(AccountRepository repo, BCryptPasswordEncoder pe) {
        this.repo = repo;
        this.pe = pe;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = repo.findById(username).orElse(null);

            assert account != null;

            String password = account.getPassword();
            String[] roles = account.getAuthorities().stream()
                    .map(Authority::getRid).toList().toArray(new String[0]);

            return User.withUsername(username)
                    .password(pe.encode(password))
                    .roles(roles)
                    .build();
        }
        catch(Exception e) {
            throw new UsernameNotFoundException(username + " not found!");
        }
    }

    public Account findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void createAccount(Account account) {
        repo.save(account);
    }

    public List<Account> findAll() {
        return repo.findAll();
    }
    public void updateImage(String uid, String image) {
        repo.updateImage(uid, image);
    }

    public void updatePassword(String uid, String pw) {
        repo.updatePassword(uid, pw);
    }

	public Account getUserByEmail(String email) {
		return repo.getUserByEmail(email);
	}
}
