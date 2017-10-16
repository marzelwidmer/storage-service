package ch.helsana.microservice.storageservice;

import ch.helsana.microservice.storageservice.infrastructure.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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

}