package com.fi.ls.service;

import com.fi.ls.security.UserPasswordEncryption;
import org.springframework.test.context.ContextConfiguration;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dao.LSUserRepository;
import com.fi.ls.entity.LSUser;
import com.fi.ls.exceptions.ServiceLayerException;
import org.springframework.dao.NonTransientDataAccessResourceException;
import java.util.concurrent.RejectedExecutionException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class LSUserServiceTest {
    
    @Mock
    private LSUserRepository lsUserRepository;
    
    @Mock
    private UserPasswordEncryption userPasswordEncryption;
    
    private LSUserService lsUserService;
    
    private LSUser user;
    
    @BeforeClass
    public void beforeClass() {
            MockitoAnnotations.initMocks(this);
            lsUserService = new LSUserServiceImpl(lsUserRepository, userPasswordEncryption);
    }
    
    @BeforeMethod
    public void beforeMethod() {
        user = new LSUser();
        user.setEmail("franta@bfu.cz");
        user.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
    }
    
    @Test
    public void testRegisterUser() {
        when(userPasswordEncryption.createHash("1234")).thenReturn("NEWtestHashtestHashtestHashtestHash7841267871s!@$%");
        
        lsUserService.registerUser(user, "1234");
        
        verify(lsUserRepository, times(1)).save(user);
        assertEquals(user.getPasswordHash(), "NEWtestHashtestHashtestHashtestHash7841267871s!@$%");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testRegisterUserThrows() {
        when(userPasswordEncryption.createHash("1234")).thenThrow(new RejectedExecutionException(""));
        
        lsUserService.registerUser(user, "1234");
        
        fail("Expected ServiceLayerException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRegisterUserLSUserNull() {
        
        lsUserService.registerUser(null, "1234");
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRegisterUserPasswordNull() {
        
        lsUserService.registerUser(user, null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testAuthenticate() {
        when(userPasswordEncryption.validatePassword(any(String.class), any(String.class))).thenReturn(true);
        
        lsUserService.authenticate(user, "1234");
        
        verify(userPasswordEncryption, times(1)).validatePassword("1234", user.getPasswordHash());
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testAuthenticateThrows() {
        when(userPasswordEncryption.validatePassword("1234", "testHashtestHashtestHashtestHash7841267871s!@$%")).thenThrow(new RejectedExecutionException(""));
        
        lsUserService.authenticate(user, "1234");
        
        fail("Expected ServiceLayerException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAuthenticateLSUserNull() {
        
        lsUserService.authenticate(null, "1234");
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAuthenticatePasswordNull() {
        
        lsUserService.authenticate(user, null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testFindById() {
        lsUserService.findById(Long.MAX_VALUE);
        
        verify(lsUserRepository, times(1)).findOne(Long.MAX_VALUE);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindByIdThrows() {
        doThrow(new NonTransientDataAccessResourceException("")).when(lsUserRepository).findOne(any(Long.class));
        
        lsUserService.findById(1L);
        
        fail("Expected ServiceLayerException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByIdNull() {
        
        lsUserService.findById(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testUpdate() {
        user.setEmail("bad.boy@thuglife.org");
        lsUserService.update(user);
        
        verify(lsUserRepository, times(1)).save(user);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testUpdateThrows() {
        doThrow(new NonTransientDataAccessResourceException("")).when(lsUserRepository).save(any(LSUser.class));
        
        lsUserService.update(user);
        
        fail("Expected ServiceLayerException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateNull() {
        
        lsUserService.update(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testRemove() {
        lsUserService.remove(user);
        
        verify(lsUserRepository, times(1)).delete(user);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testRemoveThrows() {
        doThrow(new NonTransientDataAccessResourceException("")).when(lsUserRepository).delete(any(LSUser.class));
        
        lsUserService.remove(user);
        
        fail("Expected ServiceLayerException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveNull() {
        
        lsUserService.remove(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testFindAll() {
        lsUserService.findAll();
        
        verify(lsUserRepository, times(1)).findAll();
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindAllThrows() {
        doThrow(new NonTransientDataAccessResourceException("")).when(lsUserRepository).findAll();
        
        lsUserService.findAll();
        
        fail("Expected ServiceLayerException");
    }
    
    @Test
    public void testFindByEmail() {
        lsUserService.findByEmail("franta@bfu.cz");
        
        verify(lsUserRepository, times(1)).findByEmail("franta@bfu.cz");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindByEmailThrows() {
        doThrow(new NonTransientDataAccessResourceException("")).when(lsUserRepository).findByEmail(any(String.class));
        
        lsUserService.findByEmail("franta@bfu.cz");
        
        fail("Expected ServiceLayerException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByEmailNull() {
        
        lsUserService.findByEmail(null);
        
        fail("Expected IllegalArgumentException");
    }
}
