package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.exception.UnAuthorityException;
import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.model.request.Register;
import com.example.quanlybanhang.security.jwt.JwtProvider;
import com.example.quanlybanhang.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtProvider tokenProvider;

    private final AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Login login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getEmail(),
                login.getPassword()
        );
        Authentication authentication = authenticationProvider.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("AUTHORIZATION", "Bearer " + jwt);
        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid Register register) {
        userService.register(register);
    }

    @PatchMapping("/account/setting/change-password")
    public void changePassword() {

    }

    @PostMapping("/forgot-password")
    public void forgotPassword() {

    }

    @GetMapping("/un-authorities")
    public void unAuthorities() {
        throw new UnAuthorityException("Un Authorities");
    }

}
