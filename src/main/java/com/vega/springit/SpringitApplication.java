package com.vega.springit;

import com.vega.springit.config.SpringitPropertiies;
import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import com.vega.springit.repository.CommentRepository;
import com.vega.springit.repository.LinkRepository;
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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(SpringitPropertiies.class)
@EnableJpaAuditing
public class SpringitApplication {

    @Autowired
    private SpringitPropertiies springitPropertiies;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);

    }

/*    @Bean
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
    }*/

    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
        return args -> {
            Link link = new Link("Getting Started with Spring Bott 2","https://www.google.com");
            linkRepository.save(link);

            Comment comment = new Comment("This Spring Boot 2 link is awesomw",link);
            commentRepository.save(comment);
            link.addComment(comment);

            System.out.println("We just inserted a link and a comment");

            /*Link firstLink = linkRepository.findByTitle("Getting Started with Spring Boot 2");
            System.out.println(firstLink.getTitle());*/
        };
    }
}
