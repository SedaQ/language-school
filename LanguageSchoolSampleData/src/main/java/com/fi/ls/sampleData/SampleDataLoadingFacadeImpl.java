package com.fi.ls.sampleData;

import com.fi.ls.service.*;
import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.entity.*;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.enums.UserRoles;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.facade.LSUserFacade;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Lukas Daubner (410034)
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

	final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private LectureService lectureService;
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private LSUserService userService;

	@Override
	public void loadData() {

		log.info("Loading sample data...");

		LSUser admin = new LSUser();
		admin.setEmail("admin@email.cz");
		admin.setPasswordHash("admin");
		admin.setUserRole(UserRoles.ROLE_ADMIN.name());
		
		Student s1 = new Student();
		s1.setFirstName("Arthur");
		s1.setSurname("Dent");
		s1.setBirthNumber("123456789");
		s1.setEmail("student@email.cz");
		s1.setPasswordHash("student");
		s1.setUserRole(UserRoles.ROLE_STUDENT.name());

		Student s2 = new Student();
		s2.setFirstName("Mister");
		s2.setSurname("Slartibartfast");
		s2.setBirthNumber("S15BEEPZORD78");
		s2.setEmail("slarti@constructions.magrathea");
		s2.setPasswordHash("Hash");
		s2.setUserRole(UserRoles.ROLE_STUDENT.name());

		Course c1 = new Course();
		c1.setLanguage("Errnglish");
		c1.setName("Errnglish 4 Aliens");
		c1.setProficiencyLevel(ProficiencyLevel.B2);

		Course c2 = new Course();
		c2.setLanguage("Klingon");
		c2.setName("Klingon 101");
		c2.setProficiencyLevel(ProficiencyLevel.A1);

		Lecture l11 = new Lecture();
		l11.setClassroomId("A96");
		l11.setDayTime(LocalDateTime.of(1979, Month.OCTOBER, 12, 0, 0));
		l11.setTopic("Guidenance");

		Lecture l12 = new Lecture();
		l12.setClassroomId("A96");
		l12.setDayTime(LocalDateTime.of(1980, Month.JANUARY, 1, 0, 0));
		l12.setTopic("Food and dirnk");

		Lecture l13 = new Lecture();
		l13.setClassroomId("A96");
		l13.setDayTime(LocalDateTime.of(1982, Month.JANUARY, 1, 0, 0));
		l13.setTopic("Answers");

		Lecture l21 = new Lecture();
		l21.setClassroomId("C56");
		l21.setDayTime(LocalDateTime.of(1977, Month.MAY, 25, 0, 0));
		l21.setTopic("Hope");

		Lecture l22 = new Lecture();
		l22.setClassroomId("C56");
		l22.setDayTime(LocalDateTime.of(1980, Month.MAY, 17, 0, 0));
		l22.setTopic("Empire");

		Lecture l23 = new Lecture();
		l23.setClassroomId("C56");
		l23.setDayTime(LocalDateTime.of(1983, Month.MAY, 25, 0, 0));
		l23.setTopic("Return");

		Lecturer lect1 = new Lecturer();
		lect1.setFirstName("Marvin");
		lect1.setSurname("Android");
		lect1.setNickname("Paranoid");
		lect1.setEmail("marvin.gpp@sirius.cyber.sir");
		lect1.setPasswordHash("Hash");

		Lecturer lect2 = new Lecturer();
		lect2.setFirstName("Gandalf");
		lect2.setSurname("the Gray");
		lect2.setNickname("Olórin");
		lect2.setEmail("gandalf@mainar.tol");
		lect2.setPasswordHash("Hash");

		Lecturer lect3 = new Lecturer();
		lect3.setEmail("lecturer@email.cz");
		lect3.setPasswordHash("lecturer");
		lect3.setUserRole(UserRoles.ROLE_LECTURER.name());
		lect3.setNickname("Boss");
		lect3.setFirstName("Alibaba");
		lect3.setSurname("Buh");

		Language lan11 = new Language();
		lan11.setLanguage("Errnglish");
		lan11.setProficiencyLevel(ProficiencyLevel.C1);

		Language lan21 = new Language();
		lan21.setLanguage("Trollsprak");
		lan21.setProficiencyLevel(ProficiencyLevel.B2);

		Language lan22 = new Language();
		lan22.setLanguage("dASGREf23'daz/]]@3");
		lan22.setProficiencyLevel(ProficiencyLevel.C2);

		Language lanPavel = new Language();
		lanPavel.setLanguage("Englishhhh");
		lanPavel.setProficiencyLevel(ProficiencyLevel.B2);

		lect3.addLanguage(lanPavel);
		lanPavel.setLecturer(lect3);

		lect1.addLanguage(lan11);
		lan11.setLecturer(lect1);

		lect2.addLanguage(lan21);
		lan21.setLecturer(lect2);

		lect2.addLanguage(lan22);
		lan22.setLecturer(lect2);

		// l11.addCourse(c1);
		// l12.addCourse(c1);
		// l13.addCourse(c1);
		c1.addLectures(new HashSet<>(Arrays.asList(l11, l12, l13)));

		// l22.addCourse(c2);
		// l23.addCourse(c2);
		c2.addLectures(new HashSet<>(Arrays.asList(l22, l23)));

		// l11.addLecturer(lect1);
		// l12.addLecturer(lect1);
		// l13.addLecturer(lect1);
		// l23.addLecturer(lect1);
		lect1.setListOfLectures(new HashSet<>(Arrays.asList(l11, l12, l13, l23)));

		// l22.addLecturer(lect2);
		lect2.setListOfLectures(new HashSet<>(Arrays.asList(l22)));

		// l11.addStudent(s1);
		// l12.addStudent(s1);
		// l13.addStudent(s1);
		s1.addListOfLectures(new HashSet<>(Arrays.asList(l11, l12, l13)));

		// l11.addStudent(s2);
		// l12.addStudent(s2);
		// l13.addStudent(s2);
		// l22.addStudent(s2);
		// l23.addStudent(s2);
		s2.addListOfLectures(new HashSet<>(Arrays.asList(l11, l12, l13, l22, l23)));

		userService.registerUser(admin, admin.getPasswordHash());
		userService.registerUser(lect3, lect3.getPasswordHash());
		userService.registerUser(s1, s1.getPasswordHash());
		userService.registerUser(s2, s2.getPasswordHash());

		log.info("Persisting Students");

		log.info("Persisting Lectures");
		lectureService.create(l11);
		lectureService.create(l12);
		lectureService.create(l13);
		lectureService.create(l21);
		lectureService.create(l22);
		lectureService.create(l23);
		log.info("Persisting Courses");
		courseService.create(c1);
		courseService.create(c2);
		log.info("Persisting Lecturers");
		lecturerService.create(lect1);
		lecturerService.create(lect2);
		log.info("Persisting Languages");
		languageService.create(lan11);
		languageService.create(lan21);
		languageService.create(lan22);
                languageService.create(lanPavel);
		log.info("Sample data loaded...");
	}

}
