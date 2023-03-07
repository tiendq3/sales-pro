package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entity.User;
import com.example.quanlybanhang.service.Impl.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserResource {
    @Autowired
    private UserSevice userSevice;

    @GetMapping("/users")

//    @Secured(AuthoritiesConst.ADMIN)
    public List<User> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "3") int size) {
        return userSevice.getAllUser(page, size);
    }
}
