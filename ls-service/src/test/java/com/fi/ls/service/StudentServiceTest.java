package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import org.mockito.Mock;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 *
 * @author Matúš
 */
public class StudentServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private StudentDao studentDao;
    
    private StudentService studentService;
}
