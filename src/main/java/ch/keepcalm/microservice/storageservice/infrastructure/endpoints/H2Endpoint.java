package ch.keepcalm.microservice.storageservice.infrastructure.endpoints;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * add h2-console link to list under http://localhost:8080/manage
 */
@Component
public class H2Endpoint implements Endpoint<List<String>> {
    public String getId() {
        return "h2-console";
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isSensitive() {
        return true;
    }

    public List<String> invoke() {
        return null;
    }
}
