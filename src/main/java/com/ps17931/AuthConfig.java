package com.ps17931;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

         http
             .authorizeRequests(
                 (auth) -> auth
                     .antMatchers(
                             "/account/profile",
                             "/rest/info")
                         .authenticated()
                     .antMatchers(
                             "/admin",
                             "/rest/products",
                             "/rest/authorities"
                     )
                         .hasRole("ADMIN")
                     .anyRequest()
                         .permitAll()
             );

         http
             .authorizeRequests()
             .and()
             .formLogin()
                 .loginPage("/auth/login/form")
                 .usernameParameter("username")
                 .passwordParameter("password")
                 .loginProcessingUrl("/auth/login")
                 .defaultSuccessUrl("/auth/login/success", false)
                 .failureUrl("/auth/login/failed");

         http
             .exceptionHandling()
                 .accessDeniedPage("/auth/login/403");

         http
             .logout()
             .logoutUrl("/auth/logout")
             .logoutSuccessUrl("/index");

         http
             .authorizeRequests()
             .and()
             .rememberMe()
                 .tokenRepository(this.persistentTokenRepository())
                 .tokenValiditySeconds(24 * 60 * 60); // 24h

        return http.build();
    }

    @Bean
    PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
