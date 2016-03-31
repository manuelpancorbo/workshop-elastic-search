package org.odindelrio.workshop.elasticsearch.configuration;

import org.odindelrio.workshop.elasticsearch.application.AdvertService;
import org.odindelrio.workshop.elasticsearch.domain.AdvertReader;
import org.odindelrio.workshop.elasticsearch.domain.AdvertWriter;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.advert.ElasticSearchAdvertRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.advert.JdbcAdvertRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.writer.advert.MixedAdvertWriter;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.user.InMemoryUserRepository;
import org.odindelrio.workshop.elasticsearch.infrastructure.repository.user.JdbcUserRepository;
import org.odindelrio.workshop.elasticsearch.application.UserService;
import org.odindelrio.workshop.elasticsearch.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
    public AdvertService forAdvertService(JdbcTemplate jdbcTemplate) {

        JdbcAdvertRepository jdbcAdvertReader = new JdbcAdvertRepository(jdbcTemplate);

        Set<AdvertWriter> repositories = new LinkedHashSet<>();
        repositories.add(jdbcAdvertReader);
        repositories.add(new ElasticSearchAdvertRepository());

        AdvertWriter advertWriter = new MixedAdvertWriter(repositories);

        return new AdvertService(jdbcAdvertReader, advertWriter);
    }
}
