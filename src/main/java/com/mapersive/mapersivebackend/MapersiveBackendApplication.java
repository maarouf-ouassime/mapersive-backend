package com.mapersive.mapersivebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapersive.mapersivebackend.models.Insurance;
import com.mapersive.mapersivebackend.repositories.InsuranceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MapersiveBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapersiveBackendApplication.class, args);
    }

    // @Bean
    CommandLineRunner run(InsuranceRepository insuranceRepository) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            Resource resource = new ClassPathResource("static/insurance-Data.json");
            try {
                File jsonFile = resource.getFile();
                List<Insurance> insuranceList = objectMapper.readValue(jsonFile,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Insurance.class));

                insuranceRepository.saveAll(insuranceList);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception appropriately
            }
        };
    }

}
