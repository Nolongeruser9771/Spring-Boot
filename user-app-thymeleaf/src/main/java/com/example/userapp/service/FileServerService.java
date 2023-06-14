package com.example.userapp.service;

import com.example.userapp.entity.FileServer;
import com.example.userapp.entity.User;
import com.example.userapp.exception.BadRequestException;
import com.example.userapp.exception.FileHandleException;
import com.example.userapp.exception.NotFoundException;
import com.example.userapp.repository.FileServerRepository;
import com.example.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServerService {
    @Autowired
    private FileServerRepository fileServerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FileServer> getFilesByUserId(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException("User id "+ id +" not found");
                });
        return fileServerRepository.findByUser_Id(id);
    }

    public FileServer getFileById(Integer id){
        return fileServerRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException("File id "+ id +" not found");
                });
    }

    public void deleteFile(Integer id) {
        FileServer fileServer = fileServerRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException("File id "+ id +" not found");
                });
        fileServerRepository.delete(fileServer);
    }

    public String uploadFile(Integer userId, MultipartFile file) {
        //Validate: file name, type, size
        validateFile(file);

        //is user existed?
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new NotFoundException("User id "+ userId +" not found");
                });
        try {
            FileServer fileServer = FileServer.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .user(user)
                    .build();

            fileServerRepository.save(fileServer);

            //trả về api read file
            return "api/v1/files/" + fileServer.getId();
        }catch (IOException e) {
            throw new FileHandleException("Upload file error");
        }
    }

    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // Tên file không được trống
        if (fileName == null || fileName.isEmpty()) {
            throw new BadRequestException("Invalid file name");
        }

        // Type file có nằm trong ds cho phép hay không
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if (!checkFileExtension(fileExtension)) {
            throw new BadRequestException("Invalid file type");
        }

        // Kích thước size có trong phạm vi cho phép không
        double fileSize = (double) (file.getSize() / 1_048_576);
        if (fileSize > 2) {
            throw new BadRequestException("File size larger than 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }
}
