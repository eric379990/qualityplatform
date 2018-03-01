package com.csc.qualityplatform.service;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDataService {

    List<Document> findTestDataByFunctionId(int id);

}