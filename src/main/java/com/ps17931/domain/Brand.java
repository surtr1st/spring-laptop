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
@Table(name = "Brands")
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    @Id
    private String bid;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Product> products;
}
