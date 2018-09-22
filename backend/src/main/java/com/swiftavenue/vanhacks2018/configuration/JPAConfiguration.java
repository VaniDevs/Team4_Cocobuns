package com.swiftavenue.vanhacks2018.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.swiftavenue.vanhacks2018.repositories")
public class JPAConfiguration {
}
