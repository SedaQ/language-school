/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.mapping;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.LSUser;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.enums.UserRoles;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Lukas Daubner (410034) & Pavel Šeda (441048)
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class BeanMappingTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BeanMapping beanMapping;

	@BeforeClass
	public void beforeClass() {
	}

	@Test
	public void testUserRolesMapping() {
		LSUserDTO user = new LSUserDTO();
		user.setEmail("pavelseda@email.cz");
		user.setPasswordHash("test");
		user.setUserRole(UserRoles.ROLE_ADMIN.name());

		Optional<LSUser> dto = beanMapping.mapTo(user, LSUser.class);
		assertTrue(dto.isPresent());
		assertEquals(user.getEmail(), dto.get().getEmail());
		assertEquals(user.getPasswordHash(), dto.get().getPasswordHash());
		assertEquals(user.getUserRole(), dto.get().getUserRole());
	}

	@Test
	public void testMapEntityToDTO() {
		Lecturer l = new Lecturer();
		l.setNickname("Christ");
		l.setFirstName("Jesus");
		l.setSurname("of Nazareth");
		l.setEmail("Christ@savior.org");
		l.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

		Language lan1 = new Language();
		lan1.setLanguage("English");
		lan1.setLecturer(l);
		lan1.setProficiencyLevel(ProficiencyLevel.C1);

		Language lan2 = new Language();
		lan2.setLanguage("Herbew");
		lan2.setLecturer(l);
		lan2.setProficiencyLevel(ProficiencyLevel.C1);

		l.addLanguage(lan1);
		l.addLanguage(lan2);

		Optional<LecturerDTO> dto = beanMapping.mapTo(l, LecturerDTO.class);

		assertTrue(dto.isPresent());
		assertEquals(l.getNickname(), dto.get().getNickname());
		assertEquals(l.getFirstName(), dto.get().getFirstName());
		assertEquals(l.getSurname(), dto.get().getSurname());
		assertEquals(l.getEmail(), dto.get().getEmail());
		assertEquals(l.getPasswordHash(), dto.get().getPasswordHash());
		assertEquals(l.getListOfLanguages().size(), dto.get().getListOfLanguages().size());
	}

	@Test
	public void testMapDTOToEntity() {
		LecturerDTO l = new LecturerDTO();
		l.setNickname("Christ");
		l.setFirstName("Jesus");
		l.setSurname("of Nazareth");
		l.setEmail("Christ@savior.org");
		l.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

		LanguageDTO lan1 = new LanguageDTO();
		lan1.setLanguage("English");
		lan1.setLecturer(l);
		lan1.setProficiencyLevel(ProficiencyLevel.C1);

		LanguageDTO lan2 = new LanguageDTO();
		lan2.setLanguage("Herbew");
		lan2.setLecturer(l);
		lan2.setProficiencyLevel(ProficiencyLevel.C1);

		l.addLanguage(lan1);
		l.addLanguage(lan2);

		Optional<Lecturer> dto = beanMapping.mapTo(l, Lecturer.class);

		assertTrue(dto.isPresent());
		assertEquals(l.getNickname(), dto.get().getNickname());
		assertEquals(l.getFirstName(), dto.get().getFirstName());
		assertEquals(l.getSurname(), dto.get().getSurname());
		assertEquals(l.getEmail(), dto.get().getEmail());
		assertEquals(l.getPasswordHash(), dto.get().getPasswordHash());
		assertEquals(l.getListOfLanguages().size(), dto.get().getListOfLanguages().size());
	}

	@Test
	public void testMapListOfEntitiesToListOfDTO() {
		List<Lecturer> listOfLecturers = new ArrayList<>();

		Lecturer lecturer1 = new Lecturer();
		lecturer1.setNickname("Christ");
		lecturer1.setFirstName("Jesus");
		lecturer1.setSurname("of Nazareth");
		lecturer1.setEmail("Christ@savior.org");
		lecturer1.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

		Lecturer lecturer2 = new Lecturer();
		lecturer2.setNickname("Superstar");
		lecturer2.setFirstName("Jesus");
		lecturer2.setSurname("of Nazareth");
		lecturer2.setEmail("sodomagomora@seznam.cz");
		lecturer2.setPasswordHash("testHashtestHashtestHashtestHashadasdas!@7841267871s!@$%");

		listOfLecturers.add(lecturer1);
		listOfLecturers.add(lecturer2);

		List<LecturerDTO> dto = beanMapping.mapTo(listOfLecturers, LecturerDTO.class);

		assertNotNull(dto);
		assertEquals(lecturer1.getNickname(), dto.get(0).getNickname());
		assertEquals(lecturer1.getFirstName(), dto.get(0).getFirstName());
		assertEquals(lecturer1.getSurname(), dto.get(0).getSurname());
		assertEquals(lecturer1.getEmail(), dto.get(0).getEmail());
		assertEquals(lecturer1.getPasswordHash(), dto.get(0).getPasswordHash());

		assertEquals(lecturer2.getNickname(), dto.get(1).getNickname());
		assertEquals(lecturer2.getFirstName(), dto.get(1).getFirstName());
		assertEquals(lecturer2.getSurname(), dto.get(1).getSurname());
		assertEquals(lecturer2.getEmail(), dto.get(1).getEmail());
		assertEquals(lecturer2.getPasswordHash(), dto.get(1).getPasswordHash());
	}

	@Test
	public void testMapListOfDTOToListOfEntities() {
		List<LecturerDTO> listOfLecturersDTO = new ArrayList<>();

		LecturerDTO lecturer1 = new LecturerDTO();
		lecturer1.setNickname("Christ");
		lecturer1.setFirstName("Jesus");
		lecturer1.setSurname("of Nazareth");
		lecturer1.setEmail("Christ@savior.org");
		lecturer1.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

		LecturerDTO lecturer2 = new LecturerDTO();
		lecturer2.setNickname("Superstar");
		lecturer2.setFirstName("Jesus");
		lecturer2.setSurname("of Nazareth");
		lecturer2.setEmail("sodomagomora@seznam.cz");
		lecturer2.setPasswordHash("testHashtestHashtestHashtestHashadasdas!@7841267871s!@$%");

		listOfLecturersDTO.add(lecturer1);
		listOfLecturersDTO.add(lecturer2);

		List<Lecturer> dto = beanMapping.mapTo(listOfLecturersDTO, Lecturer.class);

		assertNotNull(dto);
		assertEquals(lecturer1.getNickname(), dto.get(0).getNickname());
		assertEquals(lecturer1.getFirstName(), dto.get(0).getFirstName());
		assertEquals(lecturer1.getSurname(), dto.get(0).getSurname());
		assertEquals(lecturer1.getEmail(), dto.get(0).getEmail());
		assertEquals(lecturer1.getPasswordHash(), dto.get(0).getPasswordHash());

		assertEquals(lecturer2.getNickname(), dto.get(1).getNickname());
		assertEquals(lecturer2.getFirstName(), dto.get(1).getFirstName());
		assertEquals(lecturer2.getSurname(), dto.get(1).getSurname());
		assertEquals(lecturer2.getEmail(), dto.get(1).getEmail());
		assertEquals(lecturer2.getPasswordHash(), dto.get(1).getPasswordHash());
	}
        
        @Test
        public void testMappingCourseToDTO() {
            Course c = new Course();
            c.setId(5L);
            c.setLanguage("Course");
            c.setName("TEST");
            c.setProficiencyLevel(ProficiencyLevel.C1);
            
            Lecture l1 = new Lecture();
            l1.setId(8L);
            l1.setTopic("L1");
            l1.setClassroomId("1");
            l1.setDayTime(LocalDateTime.MIN);
            l1.addCourse(c);
            
            Lecture l2 = new Lecture();
            l2.setId(9L);
            l2.setTopic("L2");
            l2.setClassroomId("2");
            l2.setDayTime(LocalDateTime.MAX);
            l2.addCourse(c);
            
            c.setListOfLectures(Arrays.asList(l1, l2));
            
            Optional<CourseDTO> dto = beanMapping.mapTo(c, CourseDTO.class);
            assertTrue(dto.isPresent());
            assertEquals(dto.get().getId(), c.getId());
            assertEquals(dto.get().getListOfLectures().get(0).getId(), c.getListOfLectures().get(0).getId());
            
        }
        
        @Test
        public void testMappingCourseFromDTO() {
            CourseDTO c = new CourseDTO();
            c.setId(5L);
            c.setLanguage("Course");
            c.setName("TEST");
            c.setProficiencyLevel(ProficiencyLevel.C1);
            
            LectureDTO l1 = new LectureDTO();
            l1.setId(8L);
            l1.setTopic("L1");
            l1.setClassroomId("1");
            l1.setDayTime(LocalDateTime.MIN);
            l1.addCourse(c);
            
            LectureDTO l2 = new LectureDTO();
            l2.setId(9L);
            l2.setTopic("L2");
            l2.setClassroomId("2");
            l2.setDayTime(LocalDateTime.MAX);
            l2.addCourse(c);
            
            c.setListOfLectures(Arrays.asList(l1, l2));
            
            Optional<Course> entity = beanMapping.mapTo(c, Course.class);
            assertTrue(entity.isPresent());
            assertEquals(entity.get().getId(), c.getId());
            assertEquals(entity.get().getListOfLectures().get(0).getId(), c.getListOfLectures().get(0).getId());
            
        }

}
