package com.fi.ls.facade;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.entity.Language;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LanguageService;
import java.util.Optional;
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
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 *
 * @author Lukas Daubner (410034)
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LanguageFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    LanguageService languageService;
    
    @Mock
    BeanMapping beanMapping;
    
    LanguageFacade languageFacade;
    
    LanguageDTO language;
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Language())).when(beanMapping).mapTo(any(LanguageDTO.class), eq(Language.class));
        doReturn(Optional.of(new LanguageDTO())).when(beanMapping).mapTo(any(Language.class), eq(LanguageDTO.class));
        languageFacade = new LanguageFacadeImpl(languageService, beanMapping);
    }
    
    @BeforeMethod
    public void beforeMethod() {
        language = new LanguageDTO();
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(languageService);
    }
    
    @Test
    public void testCreateLanguage() {
        
        languageFacade.createLanguage(language);
        
        verify(languageService, times(1)).create(any(Language.class));
    }
    
    @Test
    public void testGetLanguageById() {
        
        languageFacade.getLanguageById(Long.MAX_VALUE);
        
        verify(languageService, times(1)).findById(any(Long.class));
    }
    
    @Test
    public void testUpdateLanguage() {
        
        languageFacade.updateLanguage(language);
        
        verify(languageService, times(1)).update(any(Language.class));
    }
    
    @Test
    public void testDeleteLanguage() {
        
        languageFacade.deleteLanguage(language);
        
        verify(languageService, times(1)).remove(any(Language.class));
    }
    
    @Test
    public void testGetAllLanguages() {
        
        languageFacade.getAllLanguages();
        
        verify(languageService, times(1)).findAll();
    }
}
