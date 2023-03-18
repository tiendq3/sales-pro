package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.dtos.CommentDTO;
import com.example.quanlybanhang.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        log.warn("[CONTROLLER] - GET COMMENT BY ID: " + id);
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertComment(CommentDTO commentDTO) {
        log.warn("[CONTROLLER] - INSERT NEW COMMENT: " + commentDTO);
        commentService.insertComment(commentDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(Long id, CommentDTO commentDTO) {
        log.warn("[CONTROLLER] - UPDATE COMMENT: " + commentDTO);
        commentService.updateComment(id, commentDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(Long id) {
        log.warn("[CONTROLLER] - DELETE COMMENT BY ID: " + id);
        commentService.deleteComment(id);
    }
}
