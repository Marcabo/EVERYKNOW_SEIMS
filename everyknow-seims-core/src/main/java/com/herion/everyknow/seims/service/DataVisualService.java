package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.service.bean.DataVisualRequest;

import java.util.List;
import java.util.Map;

/**
 * @Description 数据可视化 Service 层
 * @auther wubo25320
 * @create 2020-04-24 13:18
 */
public interface DataVisualService {

    Map<String, Map<String, Integer>> getByCondition(DataVisualRequest data);

}
