/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.mapping;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;

import java.util.ArrayList;
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

}
