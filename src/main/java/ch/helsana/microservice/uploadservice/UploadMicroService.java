package ch.helsana.microservice.uploadservice;

import ch.helsana.microservice.uploadservice.storage.StorageProperties;
import ch.helsana.microservice.uploadservice.storage.StorageService;
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
public class UploadMicroService {

    public static void main(String[] args) {
        SpringApplication.run(UploadMicroService.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}