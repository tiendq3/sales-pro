package com.example.quanlybanhang.service.other;

import com.example.quanlybanhang.model.dto.UserDTO;
import com.example.quanlybanhang.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserResourceService {

    private final UserRepository userRepository;
    

    public void deleteUser(Long id) {

    }

    public Page<UserDTO> getAllUser(int page, int size, String[] properties, Sort.Direction sort) {
        return null;
    }

    public UserDTO getUserById(Long id) {
        return null;
    }

    public void updateProduct(Long id, UserDTO userDTO) {
    }
}
