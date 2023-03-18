package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dtos.CommentDTO;
import com.example.quanlybanhang.repository.CommentRepository;
import com.example.quanlybanhang.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Page<CommentDTO> getAllComment() {
        return null;
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        return null;
    }

    @Override
    public void insertComment(CommentDTO commentDTO) {

    }

    @Override
    public void updateComment(Long id, CommentDTO commentDTO) {

    }

    @Override
    public void deleteComment(Long id) {

    }
}
