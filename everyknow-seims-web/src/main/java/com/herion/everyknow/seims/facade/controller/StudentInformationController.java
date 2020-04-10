package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.herion.everyknow.seims.dao.*;
import com.herion.everyknow.seims.service.StudentService;
import com.herion.everyknow.seims.service.bean.StudentUpload;
import com.herion.everyknow.seims.service.listener.StudentUploadListener;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.util.ResultUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 毕业生信息 Controller 层
 * @auther wubo25320
 * @create 2020-04-05 13:19
 */
@Api(value = "StudentInfoController", description = "毕业生信息管理")
@Slf4j
@RestController
@RequestMapping("/studentinfo")
public class StudentInformationController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public EKnowResponse upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        StudentUploadListener studentUploadListener = studentService.studentUploadListener();
        ExcelReader build = EasyExcel.read(multipartFile.getInputStream(), StudentUpload.class, studentUploadListener).build();
        ReadSheet studentList = EasyExcel.readSheet("studentList").build();
        build.read(studentList);
        build.finish();

        Map<String, Integer> result = new HashMap<>();
        result.put("count", studentUploadListener.getCount());
        return ResultUtils.getSuccessResponse(result);
    }

    @RequestMapping(value = "/templatedownload", method = RequestMethod.GET)
    public void templateDownload(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + StrUtil.utf8Str("student_info_template.xlsx"));
        response.setHeader("fileName", StrUtil.utf8Str("student_info_template.xlsx"));
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), StudentUpload.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
        WriteSheet studentList = EasyExcel.writerSheet("studentList").build();
        WriteSheet explain = EasyExcel.writerSheet("说明").build();
        excelWriter.write(this.getExample(), studentList);
        excelWriter.write(this.getExplain(), explain);
        excelWriter.finish();
    }

    private List<StudentUpload> getExample() {
        ArrayList<StudentUpload> uploadArrayList = new ArrayList<>();
        StudentUpload studentUpload = StudentUpload.builder()
                .stuId("8000111111")
                .stuName("张三")
                .sex("男")
                .birthDate("1999-02-01")
                .collegeName("软件学院")
                .deptName("软件工程")
                .clazz("软工161")
                .politicalstatus("共青团员")
                .identificationNumber(43028199703020838L)
                .accountType("农村")
                .nativePlace("湖北")
                .educationBackground("本科")
                .nation("汉族")
                .phone("15493437834")
                .accountLocation("南昌市")
                .entryTime("2016-07-01")
                .build();
        uploadArrayList.add(studentUpload);
        return uploadArrayList;
    }

    private List<StudentUpload> getExplain() {
        ArrayList<StudentUpload> uploadArrayList = new ArrayList<>();
        StudentUpload studentUpload = StudentUpload.builder()
                .stuId("不能为空")
                .stuName("不能为空")
                .sex("不能为空")
                .collegeName("不能为空(且必须为已有的学院)")
                .deptName("不能为空(且必须为已有的专业)")
                .clazz("不能为空(且必须为已有的班级)")
                .entryTime("不能为空(所有的时间格式为 yyyy-MM-dd)")
                .build();
        uploadArrayList.add(studentUpload);
        return uploadArrayList;
    }



}
