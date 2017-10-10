package ch.helsana.microservice.storageservice.resource.upload;

import org.springframework.stereotype.Service;

@Service
public class UploadService {

    private UploadRepository uploadRepository;

    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }




}
