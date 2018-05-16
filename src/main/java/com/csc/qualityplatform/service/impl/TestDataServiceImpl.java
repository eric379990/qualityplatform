package com.csc.qualityplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csc.qualityplatform.service.TestDataService;
import com.csc.qualityplatform.utils.MongoDBConfig;
import com.csc.qualityplatform.utils.MongoHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.result.DeleteResult;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.conversions.Bson;
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
        Document result = null;
        Boolean isNewData = data.getBoolean("isNewData");
        data.remove("isNewData");
        String res = null;
        Iterator it = data.keySet().iterator();
        while (it.hasNext()){
            String key =(String) it.next();
            if(!key.equalsIgnoreCase("functionid")) {
                doc.put("functionid", Integer.parseInt(data.getString("functionid")));
            }
            if(!key.equalsIgnoreCase("_id")) {
                doc.put(key, data.get(key));
            }
        }
        if(isNewData){
            int id = Integer.parseInt(data.getString("functionid"));
            doc.replace("functionid", id);
            table.insertOne(doc);
        }else{
            result = table.findOneAndReplace(new Document("_id", new ObjectId(data.getString("_id"))), doc);
        }

        if(result!=null&&result.containsKey("_id")){
            return "success";
        }else{
            return "failed";
        }
    }

    @Override
    public Boolean deleteTestData(String id) {
        MongoCollection<Document> table = mongoDBConfig.mongoDatabase().getCollection("testdata");
        Document filter = new Document();
        filter.put("_id", new  ObjectId(id));
        DeleteResult fi = table.deleteOne(filter);
        return fi.wasAcknowledged();
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
