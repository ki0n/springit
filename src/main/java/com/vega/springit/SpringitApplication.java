package com.vega.springit;

import javafx.application.Application;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


//@EnableJpaAuditing
@SpringBootApplication
public class SpringitApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);

    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer{
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/","classpath:/images/")
            .setCachePeriod(0);
        }
    }

    @Bean
    PrettyTime prettyTime(){
        return new PrettyTime();
    }

    @Bean
    public SpringSecurityDialect securityDialect(){return new SpringSecurityDialect();}
}
