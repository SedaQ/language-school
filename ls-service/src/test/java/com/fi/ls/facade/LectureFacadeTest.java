package com.fi.ls.facade;

import com.fi.ls.config.BeanMappingConfiguration;
import javax.transaction.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 *
 * @author Marek Nedbal (357293)
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LectureFacadeTest {
    
}
