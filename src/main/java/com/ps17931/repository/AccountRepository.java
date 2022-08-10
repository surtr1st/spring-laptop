package com.ps17931.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ps17931.domain.Account;

import javax.transaction.Transactional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Account SET image=:image WHERE username=:id")
    void updateImage(@Param("id") String uid, @Param("image") String image);

    @Modifying
    @Transactional
    @Query("UPDATE Account SET password=:password WHERE username=:id")
    void updatePassword(@Param("id") String uid, @Param("password") String password);
    
    @Query("SELECT a FROM Account a WHERE a.email=:email")
	Account getUserByEmail(@Param("email") String email);

}
