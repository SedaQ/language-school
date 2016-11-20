package com.fi.ls.facade;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LanguageService;
import javax.transaction.Transactional;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author 
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LanguageFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    LanguageService languageService;
    
    @Mock
    BeanMapping beanMapping;
    
    LecturerFacade lecturerFacade;
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void beforeMethod() {
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(languageService);
    }
    
}
