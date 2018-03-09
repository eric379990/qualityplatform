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
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TestDataServiceImpl implements TestDataService {

    @Autowired
    MongoDBConfig mongoDBConfig;

    @Override
    public List<JSONObject> getFormedTestDataByFunctionId(int id) {
        List<JSONObject> result = getTestDataByFunctionId(id);
        return result;
    }

    @Override
    public String saveTestData(JSONObject data) {
        MongoCollection<Document> table = mongoDBConfig.mongoDatabase().getCollection("testdata");
        Document doc = new Document();
        Iterator it = data.keySet().iterator();
        while (it.hasNext()){
            String key =(String) it.next();
            if(!key.equalsIgnoreCase("_id")) {
                doc.put(key, data.get(key));
            }
        }
        System.out.println("prekey:"+data.getString("_id"));
        Document result = table.findOneAndReplace(new Document("_id", new ObjectId(data.getString("_id"))), doc);
        System.out.println("key:"+((ObjectId)result.get("_id")).toHexString());
        if(result!=null&&result.containsKey("_id")){
            return "success";
        }else{
            return "failed";
        }
    }


    public List<JSONObject> getTestDataByFunctionId(int id) {
        MongoCollection<Document> table = mongoDBConfig.mongoDatabase().getCollection("testdata");
        FindIterable<Document> fi = table.find(new Document("functionid",id));
        List<JSONObject> result = new ArrayList<JSONObject>();
        for (Document o : fi) {
            JSONObject item = new JSONObject();
            Iterator it =  o.keySet().iterator();
            while (it.hasNext()){
                String key = (String)it.next();
                if(key.equalsIgnoreCase("_id")){
                    item.put("_id", ((ObjectId)o.get("_id")).toHexString());
                }else {
                    item.put(key, o.get(key));
                }
            }
            result.add(item);
        }
        return result;
    }
}
