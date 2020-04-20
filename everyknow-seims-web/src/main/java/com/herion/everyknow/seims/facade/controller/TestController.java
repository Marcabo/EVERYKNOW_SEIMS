package com.herion.everyknow.seims.facade.controller;

import cn.hutool.json.JSONUtil;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.dao.entity.UserTest;
import com.herion.everyknow.seims.service.TestService;
import com.herion.everyknow.web.request.EKnowRequest;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试 Controller
 * @auther wubo25320
 * @create 2020-03-18 9:53
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        return "我是一个返回";
    }

    /**
     * Service 层测试
     * @return
     */
    @RequestMapping("/testService")
    public String getService() {
        return testService.get();
    }

    @RequestMapping("/testQueryAll")
    public Object testQueryAll() {
        return ResultUtils.getSuccessResponse(testService.queryAll());
    }

    @RequestMapping("/testCommon")
    public EKnowResponse testCommon(@RequestBody CommonHttpRequest<UserTest> request) {
        EKnowRequest eKnowRequest = request.geteKnowRequest();
        System.out.println(eKnowRequest);
        UserTest request1 = request.getRequest();
        System.out.println(JSONUtil.parse(eKnowRequest));
        System.out.println(request1);
        System.out.println(request);
        return ResultUtils.getSuccessResponse(request1);
    }

    @RequestMapping("/testCommon1")
    public EKnowResponse testCommon1(@RequestBody UserTest request) {
        System.out.println(request);
        throw new EKnowException("id bunengweikong");
//        return ResultUtils.getSuccessResponse(request);
    }
}
