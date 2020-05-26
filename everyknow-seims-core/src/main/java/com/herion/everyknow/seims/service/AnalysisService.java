package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.service.bean.YearAnalysisRequest;

import java.util.Map;

/**
 * @Description 数据可视化 Service 层
 * @auther wubo25320
 * @create 2020-05-20 19:19
 */
public interface AnalysisService {

    /**
     * 条件查询数据可视化
     * @param request
     * @return
     */
    Map<String, Map<String, Object>> queryByCondition(YearAnalysisRequest request);
}
