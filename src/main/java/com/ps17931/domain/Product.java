package com.ps17931.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Products", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "brand", "type"
    })
})
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String name;
    private String type;
    private double price;
    private double discount;
    private int quantity;
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Cart> carts;
}
