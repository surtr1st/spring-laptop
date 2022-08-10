package com.ps17931.service;

import com.ps17931.domain.Authority;
import com.ps17931.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository repo;

    @Autowired
    private AuthorityService(AuthorityRepository repo) {
        this.repo = repo;
    }

    public List<Authority> findAll() {
        return repo.findAll();
    }

    public void updateRole(String role, int id) {
        repo.updateRole(role, id);
    }
    public void createAuthority(Authority auth) {
        repo.save(auth);
    }
    public void deleteAccount(int id) {
        repo.deleteById(id);
    }
}
