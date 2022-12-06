package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entity.User;
import com.example.quanlybanhang.model.UserModel;
import com.example.quanlybanhang.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSevice {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public boolean login(String username, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

    // admin get all user(da map thanh usermodel)
    public List<UserModel> getAllUserModel() {
        List<UserModel> userModels = userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserModel.class))
                .collect(Collectors.toList());
        return userModels;
    }

    // admin get all user(User entity)
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }


    public UserModel getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.equals(Optional.empty())) return null;
        else {
            User user = userRepository.findById(id).get();
            UserModel userModel = modelMapper.map(user, UserModel.class);
            return userModel;
        }
    }

    public boolean createUser(User user) {
        for (User user1 : getAllUser()) {
            if (user1.getUsername().equals(user.getUsername())) return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.equals(Optional.empty())) return false;
        else {
            userRepository.deleteById(id);
            return true;
        }
    }

    public boolean updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.equals(Optional.empty())) return false;
        else {
            User user1 = optionalUser.get();
            user1.setPassword(user.getPassword());
            user1.setLast_name(user.getLast_name());
            user1.setFirst_name(user.getFirst_name());
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
