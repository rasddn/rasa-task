package com.rasa.gildedrose;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
public class GildedroseApplication {

    public static void main(String[] args) {
        run(GildedroseApplication.class, args);
    }

}
