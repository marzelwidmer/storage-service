package ch.helsana.microservice.storageservice.resource.upload;

import ch.helsana.microservice.storageservice.infrastructure.domain.Upload;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author marcelwidmer
 */
public interface UploadRepository extends PagingAndSortingRepository<Upload, String> {


}
