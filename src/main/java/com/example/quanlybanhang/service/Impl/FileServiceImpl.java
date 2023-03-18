package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.entities.File;
import com.example.quanlybanhang.model.enums.EFileType;
import com.example.quanlybanhang.repository.FileRepository;
import com.example.quanlybanhang.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public Page<File> getAllFilePaging(int pageNumber, int pageSize, String[] properties, Sort.Direction sort) {
        log.warn("[SERVICE] - GET ALL FILE REQUEST");

        PageRequest pageable;
        if (sort.equals(Sort.Direction.DESC)) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, properties);
        } else
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, properties);
        return fileRepository.findAll(pageable);
    }

    @Override
    public File getFileById(Long id) {
        log.warn("[SERVICE] - GET FILE BY ID: " + id);

        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) throw new NotFoundException("Not found file by " + id);
        return optionalFile.get();
    }

    @Transactional
    @Override
    public void deleteFile(Long id) {
        log.warn("[SERVICE] - DELETE FILE BY ID: " + id);

        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) throw new NotFoundException("Not found file by " + id);
        String path = optionalFile.get().getPath();
        fileRepository.delete(optionalFile.get());
        java.io.File file = new java.io.File(path);
        file.delete();
    }

    @Transactional
    @Override
    public List<File> uploadFile(MultipartFile[] files) {
        log.warn("[SERVICE] - UPLOAD FILE REQUEST: " + Arrays.toString(files));

        if (files == null || files.length == 0) throw new RuntimeException("file mustn't null");
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            File newFile = new File();

            // set file type
            if (file == null || file.getContentType() == null) {
                throw new RuntimeException("file mustn't null!");
            } else if (file.getContentType().contains("video")) {
                newFile.setType(EFileType.VIDEO);
            } else if (file.getContentType().contains("image")) {
                newFile.setType(EFileType.IMAGE);
            } else
                throw new RuntimeException("The file must be an image or a video!");

            //set file ext
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            newFile.setExt(fileExtension);

            String randomName = UUID.randomUUID() + "." + fileExtension;
            newFile.setName(randomName);
            newFile.setSize((double) file.getSize());

            //hard code
            String filePath = "src/main/resources/images/" + randomName;
            newFile.setPath(filePath);
            fileRepository.save(newFile);
            fileList.add(newFile);

            //download file
            try {
                file.transferTo(Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fileList;
    }
}
