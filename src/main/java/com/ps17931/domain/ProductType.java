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
@Table(name = "producttypes")
@AllArgsConstructor
@NoArgsConstructor
public class ProductType implements Serializable {
    
    @Id
    private String tid;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Product> products;
}