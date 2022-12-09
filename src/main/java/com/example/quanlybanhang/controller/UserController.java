package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.entity.User;
import com.example.quanlybanhang.exceptionhandler.NotFoundException;
import com.example.quanlybanhang.model.UserModel;
import com.example.quanlybanhang.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserSevice userSevice;

    @GetMapping(value = "/login")
    public ResponseEntity login(String username, String password){
        if(userSevice.login(username,password)) return ResponseEntity.ok().body("login success!");
        return ResponseEntity.ok().body("login false!");
    }

    @GetMapping(value = "/users")
    public ResponseEntity getAllUser() {
        userSevice.getAllUser();
        return ResponseEntity.ok().body(userSevice.getAllUser());
    }

    @GetMapping("users/{id}")
    public ResponseEntity getUserById(@RequestParam("id") Long id) {
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
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        if (!userSevice.createUser(user)) return ResponseEntity.ok().body("username da ton tai");
        userSevice.createUser(user);
        return ResponseEntity.ok().body(user.toString());
    }

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam("id") Long id) {
        UserModel userNeedDel = userSevice.getUserById(id);
        if (userNeedDel == null) return ResponseEntity.ok().body("khong ton tai id");
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
    public ResponseEntity updateUser(@RequestParam("id") Long id, @RequestBody User user) {
        if (!userSevice.updateUser(id, user)) return ResponseEntity.ok().body("khong ton tai id");
        else userSevice.updateUser(id, user);
        return ResponseEntity.ok().body(userSevice.getUserById(id).toString());
    }

}
