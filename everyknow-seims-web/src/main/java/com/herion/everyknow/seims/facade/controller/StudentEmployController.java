package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.herion.everyknow.seims.dao.entity.StudentEmploymentInfo;
import com.herion.everyknow.seims.facade.request.StudentEmploymentInfoRequest;
import com.herion.everyknow.seims.facade.response.StudentEmploymentInfoResponse;
import com.herion.everyknow.seims.service.*;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 学生就业信息 Controller 层
 * @auther wubo25320
 * @create 2020-04-15 10:43
 */
@RestController
@RequestMapping("/studentemploy")
@Api(value = "StudentEmployController", description = "学生就业信息管理")
public class StudentEmployController {

    @Autowired
    private StudentEmploymentInfoService studentEmploymentInfoService;

    @Autowired
    private CompanyNatureService companyNatureService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private IndustryTypeService industryTypeService;

    @Autowired
    private EmployMethodService employMethodService;

    @RequestMapping(value = "/queryByStuId", method = RequestMethod.POST)
    @ApiOperation("根据 stuId 获取就业信息")
    public EKnowResponse queryByStuId(@RequestBody CommonHttpRequest<StudentEmploymentInfoRequest> request) {
        StudentEmploymentInfo studentEmploymentInfo = studentEmploymentInfoService.queryByStuId(request.getRequest().getStuId());
        return ResultUtils.getSuccessResponse(this.toResponse(studentEmploymentInfo));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation("添加 或 更新就业信息")
    public EKnowResponse edit(@RequestBody CommonHttpRequest<StudentEmploymentInfoRequest> request) {
        StudentEmploymentInfo studentEmploymentInfo = new StudentEmploymentInfo();
        BeanUtil.copyProperties(request.getRequest(), studentEmploymentInfo);
        studentEmploymentInfoService.editStudentEmployment(studentEmploymentInfo);
        return ResultUtils.getSuccessResponse(true);
    }


    private StudentEmploymentInfoResponse toResponse(StudentEmploymentInfo source) {
        StudentEmploymentInfoResponse response = new StudentEmploymentInfoResponse();
        BeanUtil.copyProperties(source, response);
        if (StrUtil.isNotBlank(source.getCompanyCode())) {
            response.setCompanyNatureName(companyNatureService.selectByCode(source.getCompanyCode()).getName());
        }
        if (StrUtil.isNotBlank(source.getCompanyProvince())) {
            response.setCompanyProvinceName(provinceService.selectByCode(source.getCompanyProvince()).getProvinceName());
        }
        if (StrUtil.isNotBlank(source.getCompanyCity())) {
            response.setCompanyCityName(locationService.selectByCode(source.getCompanyCity()).getCityName());
        }
        if (StrUtil.isNotBlank(source.getIndustryType())) {
            response.setIndustryTypeName(industryTypeService.selectByCode(source.getIndustryType()).getName());
        }
        if (StrUtil.isNotBlank(source.getEmployMethod())) {
            response.setEmployMethodMethod(employMethodService.selectByCode(source.getEmployMethod()).getMethod());
        }
        return response;
    }
}
