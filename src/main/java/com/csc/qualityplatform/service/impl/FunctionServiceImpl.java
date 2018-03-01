package com.csc.qualityplatform.service.impl;

import com.csc.qualityplatform.dao.FunctionRepository;
import com.csc.qualityplatform.entity.Function;
import com.csc.qualityplatform.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    FunctionRepository functionRepository;

    @Override
    public List<Function> findAllFunctions(){
        return (List<Function>)functionRepository.findAll();
    }

    @Override
    public void save(Function function) {
        functionRepository.save(function);
    }

    @Override
    public Function findByFunctionId(int functionId) {
        return this.functionRepository.findByFunctionid(functionId);
    }
}
