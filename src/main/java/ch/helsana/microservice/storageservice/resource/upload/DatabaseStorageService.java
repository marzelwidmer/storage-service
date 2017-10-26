package ch.helsana.microservice.storageservice.resource.upload;

import ch.helsana.microservice.storageservice.infrastructure.domain.StorageFile;
import ch.helsana.microservice.storageservice.infrastructure.exception.StorageException;
import ch.helsana.microservice.storageservice.infrastructure.exception.StorageFileNotFoundException;
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

    @Autowired
    public DatabaseStorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String store(MultipartFile file) {
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
            StorageFile save = storageRepository.save(storageFile);
            return save.getId();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    public StorageFile getFile(String id){
        return storageRepository.findOne(id);
    }

    public List<StorageFile> loadAll() {
        return (List<StorageFile>)storageRepository.findAll();
    }


    public Resource loadAsResource(String id) {
        StorageFile storageFile = storageRepository.findOne(id);
        try {
            Path file = Files.write(Paths.get(storageFile.getFilename()), storageFile.getData());
            Resource resource = new UrlResource(file.toUri());
            return resource;
        } catch (Exception e) {
            throw new StorageFileNotFoundException("Could not read file: " + id, e);
        }
    }


}
