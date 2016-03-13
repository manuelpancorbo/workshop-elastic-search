package org.odindelrio.workshop.elasticsearch;

import org.odindelrio.workshop.elasticsearch.configuration.ApplicationFacadeDependencyInjection;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;

@SpringCloudApplication()
@Import({ApplicationFacadeDependencyInjection.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
