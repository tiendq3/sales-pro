//package com.example.quanlybanhang.security;
//
//import com.example.quanlybanhang.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<com.example.quanlybanhang.entity.User> users = userRepository.findAll();
//        for (com.example.quanlybanhang.entity.User user : users) {
//            if (username.equals(user.getUsername())) {
//                String password = new BCryptPasswordEncoder().encode(user.getPassword());
//                return User.withUsername(username).password(password).roles("admin").build();
//            }
//
//        }
//        throw new UsernameNotFoundException(username + " does not exist");
//    }
//}