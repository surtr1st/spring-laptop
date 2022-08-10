package com.ps17931.service;

import com.ps17931.domain.Receipt;
import com.ps17931.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository repo;

    @Autowired
    private ReceiptService(ReceiptRepository repo) {
        this.repo = repo;
    }

    public List<Receipt> findAllByUsername(String username) {
        return repo.findAllByUsername(username);
    }
    public void saveReceipt(Receipt receipt) {
        repo.save(receipt);
    }
}
