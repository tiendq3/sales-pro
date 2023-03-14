package com.example.quanlybanhang.security;

import com.example.quanlybanhang.security.jwt.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**/management/**").hasAuthority(AuthorityConstants.ADMIN)
                .antMatchers("/**/register").permitAll()
                .antMatchers("/**/login").permitAll()
                .antMatchers("/**/send-otp").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                // xác thực thất bại chuyển hướng đến api /login ( bắt đăng nhập lại)
                .failureUrl("/login")
                .and()
                .exceptionHandling()
                // k đủ quyền chuyển hướng đến api /un-authority
                .accessDeniedPage("/**/un-authorities")
                .and()
                .logout();
        return http.build();
    }
}