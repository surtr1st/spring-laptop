package com.ps17931.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Receipts")
public class Receipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String name;
    private double price;
    private int quantity;
    private String address;
    @Column(name = "phonenumber")
    private String phoneNumber;
    private String username;
    @Column(name = "checkoutdate")
    private String checkoutDate;
    private String image;
}
