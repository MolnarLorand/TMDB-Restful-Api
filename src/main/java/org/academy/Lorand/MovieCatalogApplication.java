package org.academy.Lorand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;


@SpringBootApplication
@EnableSolrRepositories("org.academy.Lorand.repository.solr")
@EnableJpaRepositories("org.academy.Lorand.repository.db")
@PropertySource({"application.properties"})
public class MovieCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogApplication.class, args);
    }
}
