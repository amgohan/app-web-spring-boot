package com.entreprise.boot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.entreprise.config.WebMvcConfig;
import com.entreprise.config.WebSecurityConfig;

/**
 * Spring boot Production class.
 * @author Sidi Amine
 *
 */
@Configuration
// 
@PropertySource(value = "file:${OPENSHIFT_REPO_DIR}/conf/application.properties")
@ComponentScan(basePackages = { "com.entreprise" }, excludeFilters = { @Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class) })
@EntityScan(basePackages = { "com.entreprise.business.persistence.entities" })
@EnableJpaRepositories(basePackages = { "com.entreprise.business.persistence.repositories" })
@Import({ WebSecurityConfig.class, WebMvcConfig.class })
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ApplicationProduction {
	
	@Value("${spring.datasource.jndi-name}")
    private String jndiName;

    /**
     * 
     * @param args
     *            spring boot args
     */
    public static void main(final String[] args) {
    	SpringApplication.run(ApplicationProduction.class, args);
    }
    
    /**
     * Get the dataSource form JNDI tomcat on openshift
     * @return
     */
    @Bean
    public DataSource dataSource() {
        return new JndiDataSourceLookup().getDataSource(jndiName);
    }
}
