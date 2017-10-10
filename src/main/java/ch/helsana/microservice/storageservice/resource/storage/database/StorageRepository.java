package ch.helsana.microservice.storageservice.resource.storage.database;

import ch.helsana.microservice.storageservice.infrastructure.domain.StorageFile;
import org.springframework.data.repository.CrudRepository;

/**
 * @author marcelwidmer
 */
public interface StorageRepository extends CrudRepository<StorageFile, String> {

    StorageFile findByFilename(String name);

}
