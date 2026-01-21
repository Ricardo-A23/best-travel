package org.curso.spring.besttravel;

import lombok.extern.slf4j.Slf4j;
import org.curso.spring.besttravel.domain.repository.FlyRepository;
import org.curso.spring.besttravel.domain.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@Slf4j
public class BestTravelApplication {


    public static void main(String[] args) {
        SpringApplication.run(BestTravelApplication.class, args);
    }

}
