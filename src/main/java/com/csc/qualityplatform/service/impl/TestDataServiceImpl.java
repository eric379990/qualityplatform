package com.csc.qualityplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csc.qualityplatform.service.TestDataService;
import com.csc.qualityplatform.utils.MongoDBConfig;
import com.csc.qualityplatform.utils.MongoHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataServiceImpl implements TestDataService {

    @Autowired
    MongoDBConfig mongoDBConfig;

    @Override
    public List<Document> findTestDataByFunctionId(int id) {
        MongoCollection<Document> table = mongoDBConfig.mongoDatabase().getCollection("testdata");
        FindIterable<Document> fi = table.find(new Document("functionid",id));
        List<Document> result = new ArrayList<Document>();
        for (Document o : fi) {
            result.add(o);
        }
        return result;
    }
}
