package com.csc.qualityplatform.service;

import com.alibaba.fastjson.JSONObject;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDataService {

    List<JSONObject> getFormedTestDataByFunctionId(int id);

    String saveTestData(JSONObject data);
}