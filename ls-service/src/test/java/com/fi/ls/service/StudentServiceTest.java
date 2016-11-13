/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 *
 * @author Matúš
 */
public class StudentServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private StudentDao studentDao;
    
    @Autowired
    @InjectMocks
    private StudentService studentService;
}
