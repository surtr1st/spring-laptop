package com.ps17931.repository;

import com.ps17931.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Authority SET rid=:role WHERE id=:id")
    void updateRole(@Param("role") String role, @Param("id") int id);
}
