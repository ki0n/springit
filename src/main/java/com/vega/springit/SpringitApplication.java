package com.vega.springit;

import com.vega.springit.config.SpringitPropertiies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(SpringitPropertiies.class)
public class SpringitApplication {

    @Autowired
    private SpringitPropertiies springitPropertiies;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);

    }

    @Bean
    @Profile("dev")
    CommandLineRunner runner(){
        return args -> {
          //System.out.println("Welcome message: " + springitPropertiies.getWelcomeMsg())//
            // System.out.println("This is sometihn that we wold only do in dev");
            System.out.println("Printing out all the bean names in the application context.");
            System.out.println("-----------------------------------------------------------");
            String[] beans = applicationContext.getBeanDefinitionNames();
            Arrays.sort(beans);
            for (String bean: beans){
                //System.out.println(bean);
            }
        };
    }
}
