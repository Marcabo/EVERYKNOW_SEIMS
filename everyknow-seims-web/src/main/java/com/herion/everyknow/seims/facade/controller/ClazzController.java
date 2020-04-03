package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.entity.Clazz;
import com.herion.everyknow.seims.facade.request.ClazzRequest;
import com.herion.everyknow.seims.facade.response.ClazzResponse;
import com.herion.everyknow.seims.facade.utils.PageUtil;
import com.herion.everyknow.seims.service.ClazzService;
import com.herion.everyknow.seims.service.CollegeService;
import com.herion.everyknow.seims.service.DeptService;
import com.herion.everyknow.web.request.EKnowPageRequest;
import com.herion.everyknow.web.request.http.CommonHttpPageRequest;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.util.ResultUtils;
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
 * @Description 班级 Controller 层
 * @auther wubo25320
 * @create 2020-03-25 10:31
 */
@Api(tags = "CLazzController", description = "班级操作接口")
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ClazzService clazzService;

//    @ApiOperation("获取全部班级")
//    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
//    public EKnowResponse queryAll() {
//        List<Clazz> clazzList = clazzService.queryAll();
//        return ResultUtils.getSuccessResponse(clazzList);
//    }

    @ApiOperation("分页获取班级")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public EKnowPageResponse page(@RequestBody CommonHttpPageRequest<ClazzRequest> request) {
        IPage clazzIPage = clazzService.queryPage(PageUtil.eKnow2Plus(request));
        clazzIPage.setRecords(list2Response(clazzIPage.getRecords()));
        return PageUtil.plus2EKnow(clazzIPage);
    }

    @ApiOperation("根据 id 获取班级")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public EKnowResponse queryById(@RequestBody CommonHttpRequest<ClazzRequest> request) {
        Clazz clazz = clazzService.queryById(request.getRequest().getId());
        return ResultUtils.getSuccessResponse(clazz);
    }

//    @ApiOperation("根据条件获取班级列表")
//    @RequestMapping(value = "/queryListByCollegeCode",method = RequestMethod.POST)
//    public EKnowResponse queryListByCollegeCode(@RequestBody CommonHttpRequest<ClazzRequest> request) {
//        Clazz clazz = new Clazz();
//        BeanUtil.copyProperties(request.getRequest(), clazz);
//        List<Clazz> query = clazzService.queryLike(clazz);
//        return ResultUtils.getSuccessResponse(query);
//    }

    @ApiOperation("根据条件分页(clazzName,deptCode,CollegeCode)获取班级列表(clazzName为模糊查询)")
    @RequestMapping(value = "/queryPageByCondition",method = RequestMethod.POST)
    public EKnowPageResponse queryPageByCondition(@RequestBody CommonHttpPageRequest<ClazzRequest> request) {
        Clazz clazz = new Clazz();
        BeanUtil.copyProperties(request.getRequest(), clazz);
        IPage<Clazz> clazzIPage = clazzService.queryPageLike(PageUtil.eKnow2Plus(request), clazz);
        return PageUtil.plus2EKnow(clazzIPage);
    }

    @ApiOperation("插入班级信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public EKnowResponse insert(@RequestBody CommonHttpRequest<ClazzRequest> request) {
        Clazz clazz = new Clazz();
        BeanUtil.copyProperties(request.getRequest(), clazz);
        clazzService.insert(clazz);
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation("更新班级信息")
    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public EKnowResponse update(@RequestBody CommonHttpRequest<ClazzRequest> request) {
        Clazz clazz = new Clazz();
        BeanUtil.copyProperties(request.getRequest(), clazz);
        clazzService.update(clazz);
        return ResultUtils.getSuccessResponse(true);
    }

    @ApiOperation("删除班级信息")
    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    public EKnowResponse delete(@RequestBody CommonHttpRequest<ClazzRequest> request) {
        clazzService.deleteById(request.getRequest().getId());
        return ResultUtils.getSuccessResponse(true);
    }

    private List<ClazzResponse> list2Response(List<Clazz> clazzList) {
        List<ClazzResponse> result = new ArrayList<>();
        for (Clazz clazz : clazzList) {
            result.add(toResponse(clazz));
        }
        return result;
    }

    private ClazzResponse toResponse(Clazz clazz) {
        ClazzResponse result = new ClazzResponse();
        BeanUtil.copyProperties(clazz, result);
        result.setCollegeName(collegeService.queryByCode(clazz.getCollegeCode()).getCollegeName());
        result.setDeptName(deptService.queryByCode(clazz.getDeptCode()).getDeptName());
        return result;
    }
}
