package com.example.quanlybanhang.security;

import com.example.quanlybanhang.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.quanlybanhang.model.entity.User user = userRepository.findUserByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username + " does not exist");

        List<String> roles = user.getRoles().stream().map(r -> r.name).toList();
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}
