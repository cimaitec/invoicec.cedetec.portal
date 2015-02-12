package com.cimait.invoicec.core.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentRepositoryTest {
	
	final Logger logger = LoggerFactory.getLogger(DocumentRepositoryTest.class);
	 
    @Autowired
    private DocumentRepository documentRepository;
    
    @Test
    public void testSave() {    

    		
    }
 

    

}
