package com.csc.qualityplatform.dao;

import com.csc.qualityplatform.entity.Function;

public interface FunctionRepository extends BaseRepository<Function>{
    public Function findByFunctionid(int functionid);
}
