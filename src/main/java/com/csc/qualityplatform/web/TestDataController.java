package com.csc.qualityplatform.web;

import com.csc.qualityplatform.service.TestDataService;
import com.csc.qualityplatform.utils.MongoHandler;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestDataController {

    @Autowired
    TestDataService testDataService;

    @GetMapping("/getdata")
    public List<Document> getAllTestData(@RequestParam("id") int id){
        return testDataService.findTestDataByFunctionId(id);
    }
}
