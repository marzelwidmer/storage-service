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
@RequestMapping(value = "/storage")
public class StorageUploadController {

    private final DatabaseStorageService databaseStorageService;

    @Autowired
    public StorageUploadController(FileSystemStorageService fileSystemStorageService, DatabaseStorageService databaseStorageService) {
        this.databaseStorageService = databaseStorageService;
    }

    @PostMapping("/")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        databaseStorageService.store(file);

        return new ResponseEntity("{upload : ok}", HttpStatus.OK);
    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//        model.addAttribute("files", databaseStorageService.loadAll().stream().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(StorageUploadController.class,
//                        "serveFile",  path.getId().toString() + "/" + path.getFilename().toString()).build().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

//    @GetMapping("/files/{id:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String id) {
//        Resource file = databaseStorageService.loadAsResource(id);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }


//    @GetMapping("/files/{id:.+}/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String id, @PathVariable String filename) {
//        Resource file = databaseStorageService.loadAsResource(id);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file,
//            RedirectAttributes redirectAttributes) {
//            databaseStorageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully upload " + file.getOriginalFilename() + "!");
//        return "redirect:/storage/";
//    }

//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }

}
