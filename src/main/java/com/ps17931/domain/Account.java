package com.ps17931.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    
    @Id
    @NotEmpty(message = "Please input your username!")
    private String username;

    @Size(min = 6, max = 15, message = "Password must have the length from 6 - 15!")
    @NotNull(message = "Please input your password!")
    private String password;

    @NotEmpty(message = "Please input your email!")
    @Email(message = "Invalid email format!")
    private String email;

    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
