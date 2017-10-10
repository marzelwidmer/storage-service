package ch.helsana.microservice.storageservice.resource.storage;

import ch.helsana.microservice.storageservice.infrastructure.domain.StorageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DatabaseStorageService {

    private StorageRepository storageRepository;

    private final Path rootLocation;

    @Autowired
    public DatabaseStorageService(StorageProperties properties, StorageRepository storageRepository) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.storageRepository = storageRepository;
    }

    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            StorageFile storageFile = StorageFile.builder().data(file.getBytes())
                    .filename(file.getOriginalFilename()).build();
            storageRepository.save(storageFile);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }


    public List<StorageFile> loadAll() {
        return (List<StorageFile>)storageRepository.findAll();
    }


    public Resource loadAsResource(String filename) {
        StorageFile storageFile = storageRepository.findByFilename(filename);
        try {
            Path file = Files.write(Paths.get(storageFile.getFilename()), storageFile.getData());
            Resource resource = new UrlResource(file.toUri());
            return resource;
        } catch (Exception e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }


}
