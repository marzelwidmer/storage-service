package ch.helsana.microservice.storageservice.resource.upload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadResponse {

    private String id;

}
