package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.dao.entity.EmployMethod;
import com.herion.everyknow.seims.dao.entity.IndustryType;
import com.herion.everyknow.seims.facade.request.EmployMethodRequest;
import com.herion.everyknow.seims.facade.request.IndustryTypeRequest;
import com.herion.everyknow.seims.service.EmployMethodService;
import com.herion.everyknow.seims.service.IndustryTypeService;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 行业类别 Controller 层
 * @auther wubo25320
 * @create 2020-03-25 16:55
 */
@Api(tags = "IndustryTypeController", description = "行业类别接口")
@RestController
@RequestMapping("/industryType")
public class IndustryTypeController {

    @Autowired
    private IndustryTypeService industryTypeService;

    @ApiOperation("根据 code 获取 行业类别")
    @RequestMapping(value = "/queryByCode", method = RequestMethod.POST)
    public EKnowResponse queryByCode(@RequestBody CommonHttpRequest<IndustryTypeRequest> request) {
        IndustryType industryType = industryTypeService.selectByCode(request.getRequest().getCode());
        return ResultUtils.getSuccessResponse(industryType);
    }

    @ApiOperation("获取全部行业类别")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public EKnowResponse queryAll() {
        List<IndustryType> industryTypeList = industryTypeService.selectAll();
        return ResultUtils.getSuccessResponse(industryTypeList);
    }
}
