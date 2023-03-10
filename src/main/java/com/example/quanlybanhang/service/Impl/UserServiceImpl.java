package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.repository.UserRepository;
import com.example.quanlybanhang.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public void register(Login login) {

    }

    public void login(Login login) {

    }

    public void changePassword() {

    }
}
