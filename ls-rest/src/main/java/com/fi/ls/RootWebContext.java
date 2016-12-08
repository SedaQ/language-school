package com.fi.ls;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.rest.mixin.*;
import com.fi.ls.sampleData.SampleDataConfiguration;

@EnableWebMvc
@Configuration
@Import({BeanMappingConfiguration.class, SampleDataConfiguration.class})
@ComponentScan(basePackages = {"com.fi.ls.rest.controller", "com.fi.ls.rest.assembler"})
public class RootWebContext extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginInterceptor()); 
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    @Primary
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));
        
        objectMapper.addMixIn(LanguageDTO.class, LanguageDTOMixin.class);
        objectMapper.addMixIn(LecturerDTO.class, LecturerDTOMixin.class);
        objectMapper.addMixIn(LectureDTO.class, LectureDTOMixin.class);
        objectMapper.addMixIn(StudentDTO.class, StudentDTOMixin.class);
        objectMapper.addMixIn(CourseDTO.class, CourseDTOMixin.class);
        
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
   
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }
}
    

