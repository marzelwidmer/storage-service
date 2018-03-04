package ch.keepcalm.microservice.storageservice.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Service internal exception.
 * Created by marcelwidmer
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends Exception {

     public BusinessException(String message){
         super(message);
     }
}
