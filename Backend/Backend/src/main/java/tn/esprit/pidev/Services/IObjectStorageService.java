package tn.esprit.pidev.Services;

import org.springframework.web.multipart.MultipartFile;

public interface IObjectStorageService {
    String saveFileAlone(MultipartFile multipartFile, String FOLDER);
    void deleteFile(String fileLink);
}