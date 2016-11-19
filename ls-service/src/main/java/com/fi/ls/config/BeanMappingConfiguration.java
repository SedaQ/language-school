package com.fi.ls.config;

import javax.inject.Inject;

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

@Configuration
@Import(PersistenceApplicationContext.class)
//@ComponentScan(basePackages = { "com.fi.ls.security", "com.fi.ls.mapping", "com.fi.ls.service", "com.fi.ls.facade" })
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

	@Inject
	CourseDao courseDao;

	@Inject
	LSUserRepository lsUserRepository;

	@Inject
	LanguageDao languageDao;

	@Inject
	LectureDao lectureDao;

	@Inject
	LecturerDao lecturerDao;

	@Inject
	StudentDao studentDao;

	@Bean
	public CourseService courseService() {
		return new CourseServiceImpl(courseDao);
	}

	@Bean
	public LSUserService lsUserService() {
		return new LSUserServiceImpl(lsUserRepository, userPasswordEncryption());
	}

	@Bean
	public LanguageService languageService() {
		return new LanguageServiceImpl(languageDao);
	}

	@Bean
	public LectureService lectureService() {
		return new LectureServiceImpl(lectureDao);
	}

	@Bean
	public LecturerService lecturerService() {
		return new LecturerServiceImpl(lecturerDao);
	}

	@Bean
	public StudentService studentService() {
		return new StudentServiceImpl(studentDao);
	}

	@Bean
	public CourseFacade courseFacade() {
		return new CourseFacadeImpl(courseService(), beanMapping(dozer()));
	}

	@Bean
	public LSUserFacade lsUserFacade() {
		return new LSUserFacadeImpl(lsUserService(), beanMapping(dozer()));
	}

	@Bean
	public LanguageFacade languageFacade() {
		return new LanguageFacadeImpl(languageService(), beanMapping(dozer()));
	}

	@Bean
	public LectureFacade lectureFacade() {
		return new LectureFacadeImpl(lectureService(), beanMapping(dozer()));
	}

	@Bean
	public LecturerFacade lecturerFacade() {
		return new LecturerFacadeImpl(lecturerService(), beanMapping(dozer()));
	}

	@Bean
	public StudentFacade studentFacade() {
		return new StudentFacadeImpl(studentService(), beanMapping(dozer()));
	}
}