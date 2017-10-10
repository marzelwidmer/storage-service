package ch.helsana.microservice.storageservice;

import ch.helsana.microservice.storageservice.infrastructure.config.StorageProperties;
import ch.helsana.microservice.storageservice.resource.storage.filesystem.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Created by marcelwidmer on 04/07/16.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(StorageProperties.class)
public class StorageMicroService {

    public static void main(String[] args) {
        SpringApplication.run(StorageMicroService.class, args);
    }

    @Bean
    CommandLineRunner init(FileSystemStorageService fileSystemStorageService) {
        return (args) -> {
            fileSystemStorageService.deleteAll();
            fileSystemStorageService.init();
        };
    }
}