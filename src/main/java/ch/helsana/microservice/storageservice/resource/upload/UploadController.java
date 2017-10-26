package ch.helsana.microservice.storageservice.resource.upload;

import ch.helsana.microservice.storageservice.infrastructure.domain.StorageFile;
import ch.helsana.microservice.storageservice.infrastructure.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private final DatabaseStorageService databaseStorageService;

    @Autowired
    public UploadController(DatabaseStorageService databaseStorageService) {
        this.databaseStorageService = databaseStorageService;
    }


    @PostMapping
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        String id = databaseStorageService.store(file);
        return new ResponseEntity(FileUploadResponse.builder().storageId(id).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String id) {
        Resource file = databaseStorageService.loadAsResource(id);

        StorageFile storageFile = databaseStorageService.getFile(id);
        StorageFileResource storageFileResource = StorageFileResource.builder().filename(storageFile.getFilename()).data(storageFile.getData()).build();
        return new ResponseEntity(storageFile, HttpStatus.OK);
    }
//    @GetMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String id) {
//        Resource file = databaseStorageService.loadAsResource(id);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
