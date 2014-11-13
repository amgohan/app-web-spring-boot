package com.entreprise.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.entreprise.boot.ApplicationProduction;

/**
 * Spring boot main Class.
 * 
 * @author Sidi Amine
 *
 */
@Configuration
@PropertySource(value = "classpath:application-test.properties")
@ComponentScan(basePackages = { "com.entreprise" }, excludeFilters = { @Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApplicationProduction.class) })
@EntityScan(basePackages = { "com.entreprise.business.persistence.entities" })
@EnableJpaRepositories(basePackages = { "com.entreprise.business.persistence.repositories" })
@Import({ WebMvcConfig.class, WebSecurityConfig.class })
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class TestApplication {

    /**
     * 
     * @param args
     *            spring boot args
     */
    public static void main(final String[] args) {
	SpringApplication.run(TestApplication.class, args);
    }
}
