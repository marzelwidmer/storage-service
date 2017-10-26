package ch.helsana.microservice.storageservice.resource.upload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FileUploadResponse {

    private List<String> storageId;

}
