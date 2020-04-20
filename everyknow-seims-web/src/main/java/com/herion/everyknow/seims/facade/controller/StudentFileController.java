package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.dao.entity.StudentFile;
import com.herion.everyknow.seims.service.*;
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

/**
 * @Description 学生档案信息 Controller 层
 * @auther wubo25320
 * @create 2020-04-15 10:43
 */
@RestController
@RequestMapping("/studentfile")
@Api(value = "StudentFileController", description = "学生档案信息管理")
public class StudentFileController {

    @Autowired
    private StudentFileService studentFileService;

    @RequestMapping(value = "/queryByStuId", method = RequestMethod.POST)
    @ApiOperation("根据 stuId 获取档案信息")
    public EKnowResponse queryByStuId(@RequestBody CommonHttpRequest<StudentFile> request) {
        StudentFile studentFile = studentFileService.queryByStuId(request.getRequest().getStuId());
        return ResultUtils.getSuccessResponse(studentFile);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation("添加 或 更新档案信息")
    public EKnowResponse edit(@RequestBody CommonHttpRequest<StudentFile> request) {
        studentFileService.editStudentFile(request.getRequest());
        return ResultUtils.getSuccessResponse(true);
    }
}
