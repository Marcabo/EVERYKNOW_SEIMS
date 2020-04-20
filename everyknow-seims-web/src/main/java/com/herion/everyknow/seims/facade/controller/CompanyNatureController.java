package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.dao.entity.CompanyNature;
import com.herion.everyknow.seims.facade.request.CompanyNatureRequest;
import com.herion.everyknow.seims.service.CompanyNatureService;
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

import java.util.List;

/**
 * @Description 公司性质 Controller 层
 * @auther wubo25320
 * @create 2020-03-25 16:55
 */
@Api(tags = "CompanyNatureController", description = "公司性质接口")
@RestController
@RequestMapping("/companyNature")
public class CompanyNatureController {

    @Autowired
    private CompanyNatureService companyNatureService;

    @ApiOperation("根据 code 获取 公司性质")
    @RequestMapping(value = "/queryByCode", method = RequestMethod.POST)
    public EKnowResponse queryByCode(@RequestBody CommonHttpRequest<CompanyNatureRequest> request) {
        CompanyNature companyNature = companyNatureService.selectByCode(request.getRequest().getCode());
        return ResultUtils.getSuccessResponse(companyNature);
    }

    @ApiOperation("获取全部公司性质")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public EKnowResponse queryAll() {
        List<CompanyNature> companyNatureList = companyNatureService.selectAll();
        return ResultUtils.getSuccessResponse(companyNatureList);
    }
}
