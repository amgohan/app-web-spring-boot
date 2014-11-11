package com.entreprise.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.entreprise.config.WebMvcConfig;
import com.entreprise.config.WebSecurityConfig;

/**
 * Spring boot main Class.
 * 
 * @author Sidi Amine
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.entreprise" }, excludeFilters = { @Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApplicationProduction.class) })
@EntityScan(basePackages = { "com.entreprise.business.persistence.entities" })
@EnableJpaRepositories(basePackages = { "com.entreprise.business.persistence.repositories" })
@Import({ WebSecurityConfig.class, WebMvcConfig.class })
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class Application {

    /**
     * 
     * @param args
     *            spring boot args
     */
    public static void main(final String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}
