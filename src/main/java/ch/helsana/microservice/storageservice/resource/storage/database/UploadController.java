package ch.helsana.microservice.storageservice.resource.storage.database;

import ch.helsana.microservice.storageservice.resource.storage.filesystem.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private final DatabaseStorageService databaseStorageService;

    @Autowired
    public UploadController(FileSystemStorageService fileSystemStorageService, DatabaseStorageService databaseStorageService) {
        this.databaseStorageService = databaseStorageService;
    }


    @PostMapping("/")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        databaseStorageService.store(file);
        return new ResponseEntity(HttpStatus.OK);
    }

}
