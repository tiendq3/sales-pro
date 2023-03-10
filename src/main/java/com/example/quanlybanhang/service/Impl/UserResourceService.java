package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dto.UserDTO;
import com.example.quanlybanhang.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserResourceService {

    private final UserRepository userRepository;

    public Page<UserDTO> getAllUser() {
        return null;
    }

    public void deleteUser(Long id) {

    }

    public void updateUser(Long id, UserDTO userDTO) {

    }
}
