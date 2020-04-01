package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.bean.BeanUtil;
import com.herion.everyknow.seims.dao.entity.College;
import com.herion.everyknow.seims.facade.request.CollegeRequest;
import com.herion.everyknow.seims.service.CollegeService;
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
 * @Description 学院 Controller 层
 * @auther wubo25320
 * @create 2020-03-24 22:02
 */
@Api(tags = "college", description = "学院操作接口")
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @ApiOperation(value = "获取全部学院")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public EKnowResponse queryAll() {
        List<College> collegeList = collegeService.queryAll();
        return ResultUtils.getSuccessResponse(collegeList);
    }

    @ApiOperation(value = "插入学院信息")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public EKnowResponse insert(@RequestBody CommonHttpRequest<CollegeRequest> request) {
        College college = new College();
        BeanUtil.copyProperties(request.getRequest(), college);
        collegeService.insert(college);
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation(value = "根据 id 更新学院信息", notes = "如果修改了CollegeCode, 相对应的专业的 collegeCode也会修改")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public EKnowResponse updateById(@RequestBody CommonHttpRequest<CollegeRequest> request) {
        College college = new College();
        BeanUtil.copyProperties(request.getRequest(), college);
        collegeService.update(college);
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation(value = "根据 id 删除学院信息", notes = "将会删除学院下所有专业和班级")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public EKnowResponse deleteById(@RequestBody CommonHttpRequest<CollegeRequest> request) {
        collegeService.deleteById(request.getRequest().getId());
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation(value = "根据 collegeName 模糊查询学员信息", notes = "模糊查询")
    @RequestMapping(value = "/queryByName", method = RequestMethod.POST)
    public EKnowResponse queryByName(@RequestBody CommonHttpRequest<CollegeRequest> request) {
        List<College> collegeList = collegeService.queryByName(request.getRequest().getCollegeName());
        return ResultUtils.getSuccessResponse(collegeList);
    }

}
