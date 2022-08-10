package com.ps17931.controller;

import com.ps17931.domain.Account;
import com.ps17931.domain.Receipt;
import com.ps17931.service.AccountService;
import com.ps17931.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class AccountRestController {

    private final AccountService service;
    private final ReceiptService receiptService;
    private final HttpServletRequest request;

    private boolean isMatch = false;

    @Autowired
    private AccountRestController(AccountService service, ReceiptService receiptService, HttpServletRequest request) {
        this.service = service;
        this.receiptService = receiptService;
        this.request = request;
    }

    @GetMapping("/info")
    public ResponseEntity<Account> info() {
        Account account = service.findById(request.getRemoteUser());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/uid")
    public ResponseEntity<String> userId() {
        return new ResponseEntity<>(request.getRemoteUser(), HttpStatus.OK);
    }

    @PatchMapping("/info/update-image/{id}")
    public ResponseEntity<Void> updateImage(@RequestBody String image, @PathVariable("id") String uid) {
        service.updateImage(uid, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody String password) {
        String id = request.getRemoteUser();
        service.updatePassword(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/password/check")
    public ResponseEntity<Account> checkPassword(@RequestBody String password) {
        Account account = service.findById(request.getRemoteUser());
        isMatch = (password.equals(account.getPassword()));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/password/check")
    public String getCheckedValue() {
        return String.valueOf(isMatch);
    }

    @GetMapping("/receipt/list")
    public ResponseEntity<List<Receipt>> retrieveAllBoughtItems() {
        return new ResponseEntity<>(
                receiptService.findAllByUsername(request.getRemoteUser()),
                HttpStatus.OK
        );
    }
}
