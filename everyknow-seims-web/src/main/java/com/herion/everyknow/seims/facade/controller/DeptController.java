package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.bean.BeanUtil;
import com.herion.everyknow.seims.dao.entity.Dept;
import com.herion.everyknow.seims.facade.request.DeptRequest;
import com.herion.everyknow.seims.facade.response.DeptResponse;
import com.herion.everyknow.seims.service.CollegeService;
import com.herion.everyknow.seims.service.DeptService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 专业(Dept) Controller 层
 * @auther wubo25320
 * @create 2020-03-24 22:43
 */
@Api(value = "DeptController", description = "专业操作接口")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private CollegeService collegeService;

    @ApiOperation("获取全部专业")
    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
    public EKnowResponse queryAll() {
        List<Dept> deptList = deptService.queryAll();
        List<DeptResponse> responseList = new ArrayList<>();
        DeptResponse deptResponse;
        for (Dept dept : deptList) {
            deptResponse = new DeptResponse();
            BeanUtil.copyProperties(dept, deptResponse);
            deptResponse.setCollegeName(collegeService.queryByCode(dept.getCollegeCode()).getCollegeName());
            responseList.add(deptResponse);
        }
        return ResultUtils.getSuccessResponse(responseList);
    }

    @ApiOperation("根据 id 获取专业")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public EKnowResponse queryById(@RequestBody CommonHttpRequest<DeptRequest> request) {
        Dept dept = deptService.queryById(request.getRequest().getId());
        return ResultUtils.getSuccessResponse(dept);
    }

    @Deprecated
    @ApiOperation("根据 collegeCode 获取专业列表")
    @RequestMapping(value = "/queryListByCollegeCode",method = RequestMethod.POST)
    public EKnowResponse queryListByCollegeCode(@RequestBody CommonHttpRequest<DeptRequest> request) {
        Dept queryDept = new Dept();
        queryDept.setCollegeCode(request.getRequest().getCollegeCode());
        List<Dept> deptList = deptService.queryList(queryDept);
        List<DeptResponse> responseList = new ArrayList<>();
        DeptResponse deptResponse;
        for (Dept dept : deptList) {
            deptResponse = new DeptResponse();
            BeanUtil.copyProperties(dept, deptResponse);
            deptResponse.setCollegeName(collegeService.queryByCode(dept.getCollegeCode()).getCollegeName());
            responseList.add(deptResponse);
        }
        return ResultUtils.getSuccessResponse(responseList);
    }

    @ApiOperation("条件查询(deptName 和 collegeCode)获取专业列表(deptName为模糊)")
    @RequestMapping(value = "/queryListByCondition",method = RequestMethod.POST)
    public EKnowResponse queryListByCondition(@RequestBody CommonHttpRequest<DeptRequest> request) {
        Dept queryDept = new Dept();
        // 条件
        queryDept.setCollegeCode(request.getRequest().getCollegeCode());
        queryDept.setDeptName(request.getRequest().getDeptName());

        List<Dept> deptList = deptService.queryList(queryDept);
        List<DeptResponse> responseList = new ArrayList<>();
        DeptResponse deptResponse;
        for (Dept dept : deptList) {
            deptResponse = new DeptResponse();
            BeanUtil.copyProperties(dept, deptResponse);
            deptResponse.setCollegeName(collegeService.queryByCode(dept.getCollegeCode()).getCollegeName());
            responseList.add(deptResponse);
        }
        return ResultUtils.getSuccessResponse(responseList);
    }

    @ApiOperation("插入专业信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public EKnowResponse insert(@RequestBody CommonHttpRequest<DeptRequest> request) {
        Dept dept = new Dept();
        BeanUtil.copyProperties(request.getRequest(), dept);
        deptService.insert(dept);
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation("更新专业信息")
    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public EKnowResponse update(@RequestBody CommonHttpRequest<DeptRequest> request) {
        Dept dept = new Dept();
        BeanUtil.copyProperties(request.getRequest(), dept);
        deptService.update(dept);
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation("删除专业信息")
    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    public EKnowResponse delete(@RequestBody CommonHttpRequest<DeptRequest> request) {
        deptService.deleteById(request.getRequest().getId());
        return ResultUtils.getSuccessResponse(true);
    }
}
