package com.fi.ls.config;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The central Spring context and Spring MVC configuration. The @Configuration
 * annotation declares it as Spring configuration. The @EnableWebMvc enables
 * default MVC config for using @Controller, @RequestMapping and so on, see
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc
 * .html#mvc-config-enable
 *
 */

@EnableWebMvc
@Configuration
@Import(BeanMappingConfiguration.class)
@ComponentScan(basePackages = {"com.fi.ls.controllers"})
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(SpringMVCConfig.class);

	public static final String TEXTS = "Texts";

	/**
	 * Maps the main page to a specific view.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		logger.debug("mapping URL / to login view");
		registry.addViewController("/").setViewName("login");
	}

	/**
	 * Enables default Tomcat servlet that serves static files.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		logger.debug("enabling default servlet for static files");
		configurer.enable();
	}

	/**
	 * Provides mapping for assets
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/WEB-INF/assets/**").addResourceLocations("/WEB-INF/assets/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/bootstrap/**").addResourceLocations("/assets/bootstrap/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/css/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/font-awesome/**").addResourceLocations("/assets/font-awesome/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/ico/**").addResourceLocations("/assets/ico/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/img/**").addResourceLocations("/assets/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/js/**").addResourceLocations("/assets/js/").setCachePeriod(31556926);
    }
	
	/**
	 * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
	 */
	@Bean
	public ViewResolver viewResolver() {
		logger.debug("registering JSP in /WEB-INF/jsp/ as views");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Provides localized messages.
	 */
	@Bean
	public MessageSource messageSource() {
		logger.debug("registering ResourceBundle 'Texts' for messages");
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(TEXTS);
		return messageSource;
	}

	/**
	 * Provides JSR-303 Validator.
	 */
	@Bean
	public Validator validator() {
		logger.debug("registering JSR-303 validator");
		return new LocalValidatorFactoryBean();
	}

}
