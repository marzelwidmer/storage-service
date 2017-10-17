package ch.helsana.microservice.storageservice.resource.upload;

import ch.helsana.microservice.storageservice.infrastructure.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private final DatabaseStorageService databaseStorageService;

    @Autowired
    public UploadController(DatabaseStorageService databaseStorageService) {
        this.databaseStorageService = databaseStorageService;
    }


    @PostMapping("/")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        String id = databaseStorageService.store(file);
        return new ResponseEntity(FileUploadResponse.builder().id(id).build(), HttpStatus.OK);
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
