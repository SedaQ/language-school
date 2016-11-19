package com.fi.ls.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fi.ls.context.PersistenceApplicationContext;
import com.fi.ls.service.UserPasswordEncryption;

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = { "com.fi.ls.mapping", "com.fi.ls.service" })
public class BeanMappingConfiguration {

	@Bean
	public Mapper dozer() {
		DozerBeanMapper dozer = new DozerBeanMapper();
		return dozer;
	}

}
