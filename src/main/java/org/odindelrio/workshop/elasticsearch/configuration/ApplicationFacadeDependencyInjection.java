package org.odindelrio.workshop.elasticsearch.configuration;

import org.odindelrio.workshop.elasticsearch.infrastructure.InMemoryUserRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.JdbcUserRepository;
import org.odindelrio.workshop.elasticsearch.application.UserService;
import org.odindelrio.workshop.elasticsearch.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ApplicationFacadeDependencyInjection {
    @Bean
    @Profile("test")
    public UserRepository forUserRepositoryInTest() {
        return new InMemoryUserRepository();
    }

    @Bean
    @Profile({"dev", "pre", "pro"})
    public UserRepository forUserRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcUserRepository(jdbcTemplate);
    }

    @Bean
    public UserService forMyService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
