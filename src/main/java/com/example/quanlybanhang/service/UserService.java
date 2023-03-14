package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.model.request.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {

    @Transactional
    void register(Register register);

    void login(Login login);

    void changePassword();
}
