package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Course;
import com.example.shoppingcart.exception.FileHandleException;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{
    String FILE_PATH = "src/main/resources/static/image";
    private static CourseRepository courseRepository;

    public FileServiceImpl(CourseRepository courseRepository){
        FileServiceImpl.courseRepository = courseRepository;
    }
    @Override
    public String getThumnailById(Integer id) {
        Optional<Course> course = courseRepository.getCourseById(id);
        if (course.isPresent()){
            return course.get().getThumbnail();
        }
        throw new ResourceNotFoundException("Course id "+ id+" not found");
    }

    @Override
    public byte[] readFile(String fileName) {
        Path filePath = Path.of(FILE_PATH + "/" + fileName);
        if (Files.notExists(filePath)) {
            throw new ResourceNotFoundException(("Not found file name = " + fileName));
        }
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new FileHandleException("Error when reading file");
        }
    }
}
