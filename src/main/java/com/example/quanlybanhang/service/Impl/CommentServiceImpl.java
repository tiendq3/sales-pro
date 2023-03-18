package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.dtos.CommentDTO;
import com.example.quanlybanhang.model.entities.Comment;
import com.example.quanlybanhang.repository.CommentRepository;
import com.example.quanlybanhang.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Override
    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) throw new NotFoundException("not found comment by id: " + id);
        return modelMapper.map(comment, CommentDTO.class);
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
