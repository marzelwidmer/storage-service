package ch.keepcalm.microservice.storageservice.resource.upload;

import ch.keepcalm.microservice.storageservice.infrastructure.domain.StorageFile;
import ch.keepcalm.microservice.storageservice.infrastructure.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private final DatabaseStorageService databaseStorageService;

    @Autowired
    public UploadController(DatabaseStorageService databaseStorageService) {
        this.databaseStorageService = databaseStorageService;
    }


    @PostMapping
    public ResponseEntity mulitFileUpload(@RequestPart("ad") String adString, @RequestParam("file") List<MultipartFile> file) throws IOException {
//        FileUploadResponse jsonAd = new ObjectMapper().readValue(adString, FileUploadResponse.class);
        List<String> storageFileList =  new ArrayList<>();
        file.stream().forEach(uploadedFile -> {
            String storageId = databaseStorageService.store(uploadedFile);
            storageFileList.add(storageId);

        });
        FileUploadResponse fileUploadResponse = FileUploadResponse.builder().storageId(storageFileList).build();
        return new ResponseEntity(fileUploadResponse, HttpStatus.OK);
    }


//
//    @PostMapping
//    public ResponseEntity fileUpload(@RequestParam("file") List<MultipartFile> file) throws IOException {
//        String id = databaseStorageService.store(file.get(0));
//
//        List<String> storageFileList =  new ArrayList<>();
//        storageFileList.add(id);
//        return new ResponseEntity(FileUploadResponse.builder().storageId(storageFileList).build(), HttpStatus.OK);
//    }




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
