package ch.helsana.microservice.storageservice.resource.storage.database;

import ch.helsana.microservice.storageservice.infrastructure.exception.StorageFileNotFoundException;
import ch.helsana.microservice.storageservice.resource.storage.filesystem.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/storage")
public class StorageUploadController {

    private final DatabaseStorageService databaseStorageService;

    @Autowired
    public StorageUploadController(FileSystemStorageService fileSystemStorageService, DatabaseStorageService databaseStorageService) {
        this.databaseStorageService = databaseStorageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", databaseStorageService.loadAll().stream().map(
                path -> MvcUriComponentsBuilder.fromMethodName(StorageUploadController.class,
                        "serveFile",  path.getId().toString() + "/" + path.getFilename().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{id:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String id) {
        Resource file = databaseStorageService.loadAsResource(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping("/files/{id:.+}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String id, @PathVariable String filename) {
        Resource file = databaseStorageService.loadAsResource(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
            databaseStorageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/storage/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
