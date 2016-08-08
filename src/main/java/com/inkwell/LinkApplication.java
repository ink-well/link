package com.inkwell;

import com.inkwell.entities.Link;
import com.inkwell.entities.LinkRepository;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LinkApplication {
    private static Logger log = Logger.getLogger(LinkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LinkApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(LinkRepository repository) {
        return (args) -> {
            repository.save(new Link("Title", "kek.ru", "Lols", 5));

            log.info("Links with findall:");
            for (Link link : repository.findAll()) {
                log.info(link.toString());
            }
        };
    }
}
