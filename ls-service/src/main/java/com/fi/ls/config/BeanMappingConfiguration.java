package com.fi.ls.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fi.ls.context.PersistenceApplicationContext;
import com.fi.ls.mapping.*;
import com.fi.ls.security.*;
import com.fi.ls.dao.*;
import com.fi.ls.service.*;
import com.fi.ls.facade.*;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@Import(PersistenceApplicationContext.class)
//@ComponentScan(basePackages = { "com.fi.ls.service" })
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
        
        @Bean
        public UserPasswordEncryption userPasswordEncryption() {
            return new UserPasswordEncryption();
        }
        
        @Autowired 
        CourseDao courseDao;
        
        @Autowired 
        LSUserRepository lsUserRepository;
        
        @Autowired 
        LanguageDao languageDao;
        
        @Autowired 
        LectureDao lectureDao;
        
        @Autowired 
        LecturerDao lecturerDao;
        
        @Autowired 
        StudentDao studentDao;
                
        @Bean
        public CourseService courseSerivce() {
            return new CourseServiceImpl(courseDao);
        }
                
        @Bean
        public LSUserService lsUserService() {
            return new LSUserServiceImpl(lsUserRepository, userPasswordEncryption());
        }
        
        @Bean
        public LanguageService languageSerivce() {
            return new LanguageServiceImpl(languageDao);
        }
        
        @Bean
        public LectureService lectureSerivce() {
            return new LectureServiceImpl(lectureDao);
        }
        
        @Bean
        public LecturerService lecturerSerivce() {
            return new LecturerServiceImpl(lecturerDao);
        }
        
        @Bean
        public StudentService studentSerivce() {
            return new StudentServiceImpl(studentDao);
        }
        
        @Bean
        public CourseFacade courseFacade() {
            return new CourseFacadeImpl(courseSerivce(), beanMapping(dozer()));
        }
        
        @Bean
        public LSUserFacade lsUserFacade() {
            return new LSUserFacadeImpl(lsUserService(), beanMapping(dozer()));
        }
        
        @Bean
        public LanguageFacade languageFacade() {
            return new LanguageFacadeImpl(languageSerivce(), beanMapping(dozer()));
        }
        
        @Bean
        public LectureFacade lectureFacade() {
            return new LectureFacadeImpl(lectureSerivce(), beanMapping(dozer()));
        }
        
        @Bean
        public LecturerFacade lecturerFacade() {
            return new LecturerFacadeImpl(lecturerSerivce(), beanMapping(dozer()));
        }
        
        @Bean
        public StudentFacade studentFacade() {
            return new StudentFacadeImpl(studentSerivce(), beanMapping(dozer()));
        }
}