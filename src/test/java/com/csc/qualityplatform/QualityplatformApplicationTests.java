package com.csc.qualityplatform;

import com.mongodb.DB;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QualityplatformApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void mongoTest(){
		Document document = new Document();


	}
}
