package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.facade.request.StudentRequest;
import com.herion.everyknow.seims.facade.request.SysUserRequest;
import com.herion.everyknow.seims.facade.response.StudentResponse;
import com.herion.everyknow.seims.facade.utils.PageUtil;
import com.herion.everyknow.seims.service.*;
import com.herion.everyknow.seims.service.bean.StudentUpload;
import com.herion.everyknow.seims.service.listener.StudentUploadListener;
import com.herion.everyknow.web.request.http.CommonHttpPageRequest;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("新增学生")
    public EKnowResponse insert(@RequestBody CommonHttpRequest<StudentRequest> request) {
        Student student = new Student();
        BeanUtil.copyProperties(request.getRequest(), student);
        studentService.insert(student);
        return ResultUtils.getSuccessResponse(true);
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ApiOperation("根据 id 修改学生信息")
    public EKnowResponse updateById(@RequestBody CommonHttpRequest<StudentRequest> request) {
        Student student = new Student();
        BeanUtil.copyProperties(request.getRequest(), student);
        studentService.updateById(student);
        return ResultUtils.getSuccessResponse(true);
    }

    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    @ApiOperation("根据 id 获取毕业生基本信息")
    public EKnowResponse queryById(@RequestBody CommonHttpRequest<StudentRequest> request) {
        Student student = studentService.queryById(request.getRequest().getId());
        StudentResponse studentResponse = toResponse(student);
        return ResultUtils.getSuccessResponse(studentResponse);
    }

    @RequestMapping(value = "/queryByStuId", method = RequestMethod.POST)
    @ApiOperation("根据 学号 获取毕业生基本信息")
    public EKnowResponse queryByStuId(@RequestBody CommonHttpRequest<StudentRequest> request) {
        Student student = studentService.queryByStuId(request.getRequest().getStuId());
        return ResultUtils.getSuccessResponse(student);
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ApiOperation("分页获取毕业生基本信息")
    public EKnowPageResponse page(@RequestBody CommonHttpPageRequest<StudentRequest> request) {
        // 学院,专业,班级,姓名(模糊),学号(模糊),身份证号(模糊),入学时间,毕业届数
        // 查询接口前端传过来的 时间 都是 yyyy-MM-dd 格式的. 且都是 yyyy-01-01
        Student student = new Student();
        BeanUtil.copyProperties(request.getRequest(), student);
        IPage studentIPage = studentService.queryPage(PageUtil.eKnow2Plus(request), student);
        studentIPage.setRecords(this.toResponseList(studentIPage.getRecords()));
        return PageUtil.plus2EKnow(studentIPage);
    }

    @RequestMapping(value = "/noEmployPage", method = RequestMethod.POST)
    @ApiOperation("分页获取未就业毕业生基本信息")
    public EKnowPageResponse noEmployPage(@RequestBody CommonHttpPageRequest<StudentRequest> request) {
        // 学院,专业,班级,姓名(模糊),学号(模糊),身份证号(模糊),入学时间,毕业届数
        // 查询接口前端传过来的 时间 都是 yyyy-MM-dd 格式的. 且都是 yyyy-01-01
        Student student = new Student();
        BeanUtil.copyProperties(request.getRequest(), student);
        IPage studentIPage = studentService.queryNoEmployPage(PageUtil.eKnow2Plus(request), student);
        studentIPage.setRecords(this.toResponseList(studentIPage.getRecords()));
        return PageUtil.plus2EKnow(studentIPage);
    }

    @RequestMapping(value = "/noFilePage", method = RequestMethod.POST)
    @ApiOperation("分页获取未登记档案毕业生基本信息")
    public EKnowPageResponse noFilePage(@RequestBody CommonHttpPageRequest<StudentRequest> request) {
        // 学院,专业,班级,姓名(模糊),学号(模糊),身份证号(模糊),入学时间,毕业届数
        // 查询接口前端传过来的 时间 都是 yyyy-MM-dd 格式的. 且都是 yyyy-01-01
        Student student = new Student();
        BeanUtil.copyProperties(request.getRequest(), student);
        IPage studentIPage = studentService.queryNoFilePage(PageUtil.eKnow2Plus(request), student);
        studentIPage.setRecords(this.toResponseList(studentIPage.getRecords()));
        return PageUtil.plus2EKnow(studentIPage);
    }

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

    @RequestMapping(value = "/getAllGraduationSession", method = RequestMethod.POST)
    @ApiOperation("获取全部毕业届数")
    public EKnowResponse getAllGraduationSession() {
        List<String> allGraduationSession = studentService.getAllGraduationSession();
        Iterator<String> iterator = allGraduationSession.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (StrUtil.isBlank(next)) {
                iterator.remove();
            }
        }
        return ResultUtils.getSuccessResponse(allGraduationSession);
    }


    private List<StudentResponse> toResponseList(List<Student> studentList) {
        ArrayList<StudentResponse> responseList = new ArrayList<>();
        for (Student student : studentList) {
            responseList.add(toResponse(student));
        }
        return responseList;
    }

    private StudentResponse toResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        BeanUtil.copyProperties(student, studentResponse);
        studentResponse.setCollegeName(collegeService.queryByCode(student.getCollegeCode()).getCollegeName());
        studentResponse.setDeptName(deptService.queryByCode(student.getDeptCode()).getDeptName());
        studentResponse.setClazzName(clazzService.queryById(student.getClazzId()).getClazzName());
        return studentResponse;
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
                .politicalStatus("共青团员")
                .identificationNumber(43028199703020838L)
                .accountType("农村")
                .nativePlace("湖北省")
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
