package com.csc.qualityplatform.service;

import com.csc.qualityplatform.entity.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionService {

    List<Function> findAllFunctions();

    void save(Function function);

    Function findByFunctionId(int functionId);
}
