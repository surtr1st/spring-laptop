package com.ps17931.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Carts" , uniqueConstraints = {
        @UniqueConstraint(columnNames = { "productid" })
})
public class Cart implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String image;
    private String username;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;
}
