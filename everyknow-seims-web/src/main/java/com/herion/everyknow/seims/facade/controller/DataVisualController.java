package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.service.DataVisualService;
import com.herion.everyknow.seims.service.bean.DataVisualRequest;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description 数据可视化 Controller 层
 * @auther wubo25320
 * @create 2020-04-24 13:09
 */
@Api(tags = "DataVisualController", description = "数据可视化接口")
@RestController
@RequestMapping("/datavisual")
public class DataVisualController {

    @Autowired
    private DataVisualService dataVisualService;

    @ApiOperation("条件获取数据")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public EKnowResponse get(@RequestBody CommonHttpRequest<DataVisualRequest> request) {
        Map<String, Map<String, Integer>> byCondition = dataVisualService.getByCondition(request.getRequest());
        return ResultUtils.getSuccessResponse(byCondition);
    }
}
