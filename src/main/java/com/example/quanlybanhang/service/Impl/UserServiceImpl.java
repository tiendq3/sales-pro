package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.dto.UserDTO;
import com.example.quanlybanhang.model.entity.User;
import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.repository.UserRepository;
import com.example.quanlybanhang.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public boolean login(String username, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }


    public void register(Login login) {
    }

    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.equals(Optional.empty())) {
            throw new NotFoundException("khong ton tai user");
        } else {
            User user = userRepository.findById(id).get();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }
    }
}
