package vn.techmaster.usermanagement.service;

public interface FileService {
    String getAvatarById(Integer id);

    byte[] readFile(String fileName);
}
