package com.example.quanlybanhang.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser(customUserDetailService.loadUserByUsername().getUsername())
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/user/id").hasRole("admin")
                .antMatchers("/user").hasRole("user")
                .antMatchers("api/product/category").hasRole("user")
                .antMatchers("api/product/name").hasRole("user")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .permitAll()
//                .and()
//                .logout();
    }
}