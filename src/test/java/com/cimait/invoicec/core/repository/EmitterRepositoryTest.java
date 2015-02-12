package com.cimait.invoicec.core.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cimait.invoicec.core.config.PersistenceContext;
import com.cimait.invoicec.core.entity.Emitter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class EmitterRepositoryTest {

    //final Logger logger = LoggerFactory.getLogger(EmitterRepositoryTest.class);
 
	@Autowired
    private EmitterRepository emitterRepository;
    
	//@Autowired
	//private Emitter emitter;
        
    @Before
    public void setUp() {}
    
    
    @Test
    public void testFind() {
    	
    	Emitter encontrado = emitterRepository.findOne(new Long(1));
    	System.out.println(encontrado.getBusinessName());
    	
    }   
    
    
   
}
