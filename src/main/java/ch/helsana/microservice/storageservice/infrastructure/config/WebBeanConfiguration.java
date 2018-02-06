package ch.helsana.microservice.storageservice.infrastructure.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;

/**
 * Created by marcelwidmer on 21/03/16.
 */
@Configuration
@EnableEntityLinks
public class WebBeanConfiguration extends WebMvcConfigurerAdapter {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Mapper dozerBeanMapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return beanMapper;
    }

}