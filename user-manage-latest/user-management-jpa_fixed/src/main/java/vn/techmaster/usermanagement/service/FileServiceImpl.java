package vn.techmaster.usermanagement.service;

import org.springframework.stereotype.Service;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.exception.FileHandleExeption;
import vn.techmaster.usermanagement.exception.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    private static UserService userService;

    public FileServiceImpl(UserService userService){
        FileServiceImpl.userService = userService;
    }

    @Override
    public String getAvatarById(Integer id) {
        UserDTO user = userService.findUserById(id);
        return user.avatar();
    }

    @Override
    public byte[] readFile(String fileName) {
        Path filePath = Path.of("upload/"+fileName);
        if (Files.notExists(filePath)) {
            throw new NotFoundException(("Not found file name = " + fileName));
        }
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new FileHandleExeption("Error when reading file");
        }
    }
}
