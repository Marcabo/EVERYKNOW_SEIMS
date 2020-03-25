package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.dao.entity.CompanyNature;
import com.herion.everyknow.seims.dao.entity.EmployMethod;
import com.herion.everyknow.seims.facade.request.CompanyNatureRequest;
import com.herion.everyknow.seims.facade.request.EmployMethodRequest;
import com.herion.everyknow.seims.service.CompanyNatureService;
import com.herion.everyknow.seims.service.EmployMethodService;
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
 * @Description 就业方式 Controller 层
 * @auther wubo25320
 * @create 2020-03-25 16:55
 */
@Api(tags = "EmployMethodController", description = "就业方式接口")
@RestController
@RequestMapping("/employMethod")
public class EmployMethodController {

    @Autowired
    private EmployMethodService employMethodService;

    @ApiOperation("根据 code 获取 就业方式")
    @RequestMapping(value = "/queryByCode", method = RequestMethod.POST)
    public EKnowResponse queryByCode(@RequestBody CommonHttpRequest<EmployMethodRequest> request) {
        EmployMethod employMethod = employMethodService.selectByCode(request.getRequest().getCode());
        return ResultUtils.getSuccessResponse(employMethod);
    }

    @ApiOperation("获取全部就业方式")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public EKnowResponse queryAll() {
        List<EmployMethod> employMethodList = employMethodService.selectAll();
        return ResultUtils.getSuccessResponse(employMethodList);
    }
}
