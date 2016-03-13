package org.odindelrio.workshop.elasticsearch.configuration;

import org.odindelrio.workshop.elasticsearch.application.AdvertService;
import org.odindelrio.workshop.elasticsearch.domain.AdvertRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.advert.JdbcAdvertRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.user.InMemoryUserRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.user.JdbcUserRepository;
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
    public UserService forUserService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public AdvertRepository forAdvertRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcAdvertRepository(jdbcTemplate);
    }

    @Bean
    public AdvertService forAdvertService(AdvertRepository advertRepository) {
        return new AdvertService(advertRepository);
    }
}
