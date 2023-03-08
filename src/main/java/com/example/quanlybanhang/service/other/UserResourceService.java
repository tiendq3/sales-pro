package com.example.quanlybanhang.service.other;

import com.example.quanlybanhang.model.dto.UserDTO;
import com.example.quanlybanhang.model.entity.User;
import com.example.quanlybanhang.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserResourceService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    // admin get all user(da map thanh usermodel)
    public List<UserDTO> getAllUserModel() {
        List<UserDTO> userDTOS = userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return userDTOS;
    }

    // admin get all user(User entity)
    // pageable
    public List<User> getAllUser(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        List<User> users = userRepository.findAll(paging).getContent();
        return users;
    }


    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.equals(Optional.empty())) return false;
        else {
            userRepository.deleteById(id);
            return true;
        }
    }

    @Transactional
    public boolean updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.equals(Optional.empty())) return false;
        else {
            User user1 = optionalUser.get();
            user1.setPassword(user.getPassword());
            user1.setGender(user.getGender());
            user1.setEmail(user.getEmail());
            user1.setAvatar(user.getAvatar());
            user1.setPhone(user.getPhone());
            user1.setAddress(user.getAddress());
            userRepository.save(user1);
            return true;
        }
    }
}
