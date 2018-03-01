package com.csc.qualityplatform.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;
import java.util.Arrays;

@Configuration  //等价于XML中配置bean
@PropertySource(value = "classpath:application.properties",ignoreResourceNotFound = true)
public class MongoDBConfig {
    @Value("${mongodb.schema}")
    private String databaseName;

    @Value("${mongodb.uri}")
    private String uri;

    @Value("${mongodb.port}")
    private int port;

    @Value("${mongodb.username}")
    private String userName;

    @Value("${mongodb.password}")
    private String password;

    @Bean
    public MongoDatabase mongoDatabase() {
        MongoCredential credential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
        ServerAddress addr = new ServerAddress(uri, port);
        MongoClient client = new MongoClient(addr, Arrays.asList(credential));
        MongoDatabase mdb = client.getDatabase(databaseName);
        return mdb;
    }
}