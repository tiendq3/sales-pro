package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entity.User;
import com.example.quanlybanhang.service.other.UserResourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management")
@AllArgsConstructor
@Slf4j
public class UserResource {
    private final UserResourceService userResourceService;

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "3") int size) {
        return userResourceService.getAllUser(page, size);
    }
}
