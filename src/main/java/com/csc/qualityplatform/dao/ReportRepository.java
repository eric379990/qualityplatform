package com.csc.qualityplatform.dao;

import com.csc.qualityplatform.entity.Function;

public interface ReportRepository extends BaseRepository<Function>{
    public Function findByFunctionid(int functionid);
}
