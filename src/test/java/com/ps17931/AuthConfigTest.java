package com.ps17931;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertDelegateTarget;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthConfigTest {
    
    @Test
    public void usernameTest(HttpSecurity http) throws Exception {
        assertEquals(http.formLogin().usernameParameter("username"), "user");
    }
}
