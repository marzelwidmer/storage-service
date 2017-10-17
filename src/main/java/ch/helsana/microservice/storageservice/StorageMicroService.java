package ch.helsana.microservice.storageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by marcelwidmer on 04/07/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class StorageMicroService {

    public static void main(String[] args) {
        SpringApplication.run(StorageMicroService.class, args);
    }

}