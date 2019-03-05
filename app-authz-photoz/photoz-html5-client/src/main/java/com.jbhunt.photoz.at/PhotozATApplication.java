package com.jbhunt.photoz.at;

import net.rossillo.spring.web.mvc.CacheControl;
import net.rossillo.spring.web.mvc.CacheControlHandlerInterceptor;
import net.rossillo.spring.web.mvc.CachePolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@CacheControl(policy = CachePolicy.NO_CACHE)
public class PhotozATApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PhotozATApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PhotozATApplication.class);
    }

    @Bean
    public CacheControlHandlerInterceptor cacheControlHandlerInterceptor() {
        return new CacheControlHandlerInterceptor();
    }
}
