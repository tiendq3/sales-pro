package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
}
