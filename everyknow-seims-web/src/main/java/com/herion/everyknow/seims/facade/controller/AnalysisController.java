package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.service.AnalysisService;
import com.herion.everyknow.seims.service.bean.YearAnalysisRequest;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description 历年分析对比 Controller层
 * @auther wubo25320
 * @create 2020-04-30 14:11
 */
@Api("历年分析对比接口层")
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping("/test")
    public EKnowResponse test() {
        return ResultUtils.getSuccessResponse("我成功了!");
    }

    @RequestMapping("/get")
    public EKnowResponse get(@RequestBody CommonHttpRequest<YearAnalysisRequest> request) {
        Map<String, Map<String, Object>> stringMapMap = analysisService.queryByCondition(request.getRequest());
        return ResultUtils.getSuccessResponse(stringMapMap);
    }
}
