package com.csc.qualityplatform.utils;


import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MongoHandler {

    public static MongoDatabase mongoDatabase() {
        MongoClient client = null;
        MongoDatabase mdb = null;
        try {
            // 用户名 数据库 密码
            MongoCredential credential = MongoCredential.createCredential("zxjtUser", "zxjt", "zxjtPass".toCharArray());
            //IP port
            ServerAddress addr = new ServerAddress("10.237.3.81", 3389);
            client = new MongoClient(addr, Arrays.asList(credential));
            //得到数据库
            mdb = client.getDatabase("zxjt");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return mdb;
    }
}
