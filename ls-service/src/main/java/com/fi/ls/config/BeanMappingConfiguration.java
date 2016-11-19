package com.fi.ls.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fi.ls.context.PersistenceApplicationContext;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.mapping.BeanMappingImpl;

@Configuration
@Import(PersistenceApplicationContext.class)
//@ComponentScan(basePackages = { "com.fi.ls.service", "com.fi.ls.security" })
public class BeanMappingConfiguration {

	@Bean
	public Mapper dozer() {
		DozerBeanMapper dozer = new DozerBeanMapper();
		return dozer;
	}
        
        @Bean
        public BeanMapping beanMapping(Mapper mapper) {
            return new BeanMappingImpl(dozer());
        }
}