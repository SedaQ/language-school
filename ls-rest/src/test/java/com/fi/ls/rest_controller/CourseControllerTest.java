package com.fi.ls.rest_controller;

import com.fi.ls.RootWebContext;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.rest.controller.CoursesController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static javax.swing.UIManager.get;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 *
 * @author Matúš
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class CourseControllerTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private CourseFacade courseFacade;
    
    @Autowired
    @InjectMocks
    private CoursesController coursesController;
    
    private MockMvc mockMvc;
    
    @BeforeClass
    public void beforeClass(){
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(coursesController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }
    
    @Test
    public void getAllCourses() throws Exception{
        doReturn(Collections.unmodifiableList(this.createCourses())).when(courseFacade).getAllCourses();
        
        mockMvc.perform((RequestBuilder) get("/pa165/rest/courses"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }
    
    private List<CourseDTO> createCourses(){
        CourseDTO c1 = new CourseDTO();
        CourseDTO c2 = new CourseDTO();
        
        c1.setLanguage("AJ");
        c2.setLanguage("GER");
        
        c1.setName("test1");
        c2.setName("test2");
        
        c1.setProficiencyLevel(ProficiencyLevel.A1);
        c2.setProficiencyLevel(ProficiencyLevel.A2);
        
        return Arrays.asList(c1, c2);
    }
}
