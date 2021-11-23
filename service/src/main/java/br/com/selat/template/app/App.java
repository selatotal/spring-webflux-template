package br.com.selat.template.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "br.com.selat.template")
@ComponentScan(basePackages = "br.com.selat.template")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
