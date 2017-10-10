package ch.helsana.microservice.storageservice.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.storage")
public class StorageProperties {


    @Value(value = "${location:${java.io.tmpdir}/upload-dir/}")
    private String storageLocation;

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return storageLocation;
    }

    public void setLocation(String location) {
        this.storageLocation = location;
    }

}
