package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entity.File;
import com.example.quanlybanhang.service.Impl.FileServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class FileController {
    private final FileServiceImpl fileServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @GetMapping("/management/files")
    public ResponseEntity<Page<File>> getAllFile(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(defaultValue = "name") String[] properties,
                                                 @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        logger.info("GET all file request");
        Page<File> filePaging = fileServiceImpl.getAllFilePaging(pageNumber, pageSize, properties, sort);
        return ResponseEntity.status(HttpStatus.OK).body(filePaging);
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fileServiceImpl.getFileById(id));
    }

    @PostMapping("/management/files/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public List<File> uploadFile(@RequestParam("files") MultipartFile[] files) {
        logger.info("UPLOAD FILE");
        return fileServiceImpl.uploadFile(files);
    }

    @DeleteMapping("/management/files/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable Long id) {
        fileServiceImpl.deleteFile(id);
    }


}