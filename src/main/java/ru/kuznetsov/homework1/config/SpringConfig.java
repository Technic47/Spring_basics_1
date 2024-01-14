package ru.kuznetsov.homework1.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("ru.kuznetsov.homework1")
@EnableJpaRepositories("ru.kuznetsov.homework1.repositories")
@EntityScan("ru.kuznetsov.homework1.models")
@EnableWebMvc
public class SpringConfig {

}
