package com.codecool.octogoods;

import com.codecool.octogoods.utils.StaticDBLoader;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OctoGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OctoGoodsApplication.class, args);
    }

    @Bean
    public CommandLineRunner clr(ApplicationContext ctx) {
        return args -> {
            System.out.println("Loading default data set..");

            StaticDBLoader loader = ctx.getBean(StaticDBLoader.class);

            loader.loadInitialActionStatuses();
            loader.loadInitialUsers();
            loader.loadInitialCategories();
            loader.loadInitialItems();
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
