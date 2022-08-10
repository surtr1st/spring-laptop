package com.ps17931.controller;

import com.ps17931.domain.Authority;
import com.ps17931.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class AuthorityRestController {

    private final AuthorityService service;

    @Autowired
    private AuthorityRestController(AuthorityService service) {
        this.service = service;
    }

    @GetMapping("/authorities")
    public ResponseEntity<List<Authority>> dashboard() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/update-role/{id}")
    public ResponseEntity<String> updateRole(@RequestBody String role, @PathVariable("id") int id) {
        service.updateRole(role, id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") int id) {
        service.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
