package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entity.User;
import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.dto.UserDTO;
//import com.example.quanlybanhang.security.CustomUserDetailService;
import com.example.quanlybanhang.service.Impl.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserSevice userSevice;
//    @Autowired
//    private CustomUserDetailService customUserDetailService;
//    @GetMapping("/current-user")
//    public ResponseEntity<?> getCurrentUser() {
//        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
//    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> login(String username, String password) {
        if (userSevice.login(username, password)) return ResponseEntity.ok().body("login success!");
        return ResponseEntity.ok().body("login false!");
    }

//    @GetMapping(value = "/users")
//    public ResponseEntity<?> getAllUser() {
//        userSevice.getAllUser();
//        return ResponseEntity.ok().body(userSevice.getAllUser());
//    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUserById(@RequestParam("id") Long id) {
//        if (userSevice.getUserById(id) == null) return ResponseEntity.ok().body("khong ton tai tai khoan");
        if (userSevice.getUserById(id) == null) throw new NotFoundException("Not found user");
        return ResponseEntity.ok().body(userSevice.getUserById(id));
    }

    @RequestMapping(
            path = "/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE
            })
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userSevice.createUser(user);
        return ResponseEntity.ok().body(user.toString());
    }

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserDTO userNeedDel = userSevice.getUserById(id);
        userSevice.deleteUser(id);
        return ResponseEntity.ok().body(userNeedDel.toString());
    }

    @RequestMapping(
            path = "/users/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE
            }
    )
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        if (!userSevice.updateUser(id, user)) return ResponseEntity.ok().body("khong ton tai id");
        else userSevice.updateUser(id, user);
        return ResponseEntity.ok().body(userSevice.getUserById(id).toString());
    }

}
