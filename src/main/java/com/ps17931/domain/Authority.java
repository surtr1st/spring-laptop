package com.ps17931.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Authorities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "username", "rid"
    })
})
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    private String rid;

}
