package com.csc.qualityplatform.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csc.qualityplatform.entity.Function;
import com.csc.qualityplatform.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class FunctionController {

    @Autowired
    private FunctionService functionService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/findbyId")
    public Function findFunctionById(@RequestParam("id") int id){
        return functionService.findByFunctionId(id);
    }

    @GetMapping("/getApiList")
    public List<JSONObject> findAllFunctions(){
         List<Function> allFunctions = functionService.findAllFunctions();
         List<JSONObject> data = new ArrayList<JSONObject>();
         Iterator<Function> it = allFunctions.iterator();
         while (it.hasNext()){
             Object o = it.next();
             JSONObject item = new JSONObject();
             item.put("label", ((Function)o).getUrl());
             item.put("value", ((Function)o).getFunctionid());
             data.add(item);
         }
         return data;
    }
}
