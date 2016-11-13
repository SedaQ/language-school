/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.mapping;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author lukas
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class BeanMappingTest {
    
    @Inject
    private BeanMapping beanMapping;
    
    @BeforeClass
    public void beforeClass() {
    }
    
    /*
    @Test
    public void mapLanguageEntitytoDTO() {
        Lecturer l = new Lecturer();
        l.setNickname("Dude");
        l.setFirstName("Where is my");
        l.setSurname("Car?");
        
        Language lan = new Language();
        lan.setLanguage("English");
        lan.setLecturer(l);
        lan.setProficiencyLevel(ProficiencyLevel.C1);
        
        Optional<LanguageDTO> dto = beanMapping.mapTo(lan, LanguageDTO.class);
        
        assertTrue(dto.isPresent());
        assertEquals(lan.getLanguage(), dto.get().getLanguage());
        assertEquals(lan.getProficiencyLevel(), dto.get().getProficiencyLevel());
        assertEquals(lan.getLecturer(), dto.get().getLecturer());
        }
    */
}
