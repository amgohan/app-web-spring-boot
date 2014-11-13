package com.entreprise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author Sidi Amine
 *
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Value("${app.mvc.locale}")
	private String locale;
	
	@Value("${app.mvc.locale.cookieName}")
	private String localeCookieName;
	
	@Value("${app.mvc.locale.cookieName.maxAge}")
	private Integer localeCookieMaxAge;

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		// @formatter:off
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
		// @formatter:on
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/404").setViewName("errors/404");
        registry.addViewController("/500").setViewName("errors/500");
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("locale/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
    public LocaleResolver localeResolver() {
		CookieLocaleResolver clr = new CookieLocaleResolver();
		clr.setCookieName(localeCookieName);
		clr.setCookieMaxAge(localeCookieMaxAge);
		clr.setDefaultLocale(StringUtils.parseLocaleString(locale));
        return clr;
    }
	
	@Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName(localeCookieName);
        return lci;
    }
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	            container.addErrorPages(
	            		new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
	            		new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500")
	            		);
	        }
	    };
	}
}
