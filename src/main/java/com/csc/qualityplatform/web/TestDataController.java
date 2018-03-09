package com.csc.qualityplatform.web;

import com.alibaba.fastjson.JSONObject;
import com.csc.qualityplatform.service.TestDataService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TestDataController {

    @Autowired
    TestDataService testDataService;

    @GetMapping("/getApiParameterDataInfo")
    public List<JSONObject> getAllTestData(@RequestParam("id") int id){
        return testDataService.getFormedTestDataByFunctionId(id);
    }

    @PostMapping("/saveApiDataInfo")
    public String saveTestData(@RequestBody JSONObject data){
        return testDataService.saveTestData(data);
    }
}
