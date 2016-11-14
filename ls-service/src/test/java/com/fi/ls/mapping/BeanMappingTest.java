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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author lukas
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class BeanMappingTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private BeanMapping beanMapping;
    
    @BeforeClass
    public void beforeClass() {
    }
    
    /*
    @Test
    public void mapEntityToDTO() {
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
    */
    
    /*
    @Test
    public void mapDTOToEntity() {
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
    */
}
