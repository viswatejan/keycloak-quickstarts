package com.jbhunt.photoz;

import net.rossillo.spring.web.mvc.CacheControl;
import net.rossillo.spring.web.mvc.CacheControlHandlerInterceptor;
import net.rossillo.spring.web.mvc.CachePolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@CacheControl(policy = CachePolicy.NO_CACHE)
public class PhotozApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PhotozApplication.class, args);
    }

    @Bean
    public CacheControlHandlerInterceptor cacheControlHandlerInterceptor() {
        return new CacheControlHandlerInterceptor();
    }
}
