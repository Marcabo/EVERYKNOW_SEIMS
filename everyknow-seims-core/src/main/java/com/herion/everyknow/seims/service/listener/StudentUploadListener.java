package com.herion.everyknow.seims.service.listener;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.DateUtils;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.dao.*;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.bean.StudentUpload;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Student 的读取类
 * @auther wubo25320
 * @create 2020-04-05 16:03
 */
@Slf4j
public class StudentUploadListener extends AnalysisEventListener<StudentUpload> {

    /**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private Integer count = 0;

    private List<StudentUpload> list = new ArrayList<>();

    private StudentDao studentDao;

    private CollegeDao collegeDao;

    private DeptDao deptDao;

    private ClazzDao clazzDao;

    private ProvinceDao provinceDao;

    private LocationDao locationDao;

    public StudentUploadListener(StudentDao studentDao, CollegeDao collegeDao, DeptDao deptDao, ClazzDao clazzDao, ProvinceDao provinceDao, LocationDao locationDao) {
        this.studentDao = studentDao;
        this.collegeDao = collegeDao;
        this.deptDao = deptDao;
        this.clazzDao = clazzDao;
        this.provinceDao = provinceDao;
        this.locationDao = locationDao;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void invoke(StudentUpload studentUpload, AnalysisContext analysisContext) {
        log.info("解析到一条数据: {}", JSONUtil.parseObj(studentUpload, false, true));
        list.add(studentUpload);
        if (list.size() > StudentUploadListener.BATCH_COUNT) {
            this.saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        this.saveData();
    }

    private void saveData() {
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            for (StudentUpload studentUpload : list) {
                studentList.add(toStudent(studentUpload));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int insertCount = studentDao.insertBatch(studentList);
        this.count += insertCount;
    }

    /**
     * excel表格数据
     *
     * @param studentUpload
     * @return
     */
    private Student toStudent(StudentUpload studentUpload) throws ParseException {
        Student student = Student.builder()
                .stuId(studentUpload.getStuId())
                .stuName(studentUpload.getStuName())
                .sex(toSex(studentUpload.getSex()))
                .birthDate(DateUtils.parseDate(studentUpload.getBirthDate(), "yyyy-MM-dd"))
                .collegeCode(collegeDao.queryByName(studentUpload.getCollegeName()).get(0).getCollegeCode())
                .deptCode(deptDao.queryByName(studentUpload.getDeptName()).get(0).getDeptCode())
                .clazzId(clazzDao.queryByName(studentUpload.getClazz()).get(0).getId())
                .politicalstatus(studentUpload.getPoliticalstatus())
                .identificationNumber(studentUpload.getIdentificationNumber())
                .accountType(studentUpload.getAccountType())
                .accountLocationCode(locationDao.queryByCityName(studentUpload.getAccountLocation()).get(0).getCode())
                .nativePlace(studentUpload.getNativePlace())
                .educationBackground(studentUpload.getEducationBackground())
                .nation(studentUpload.getNation())
                .phone(studentUpload.getPhone())
                .wechatCode(studentUpload.getWechatCode())
                .qqCode(studentUpload.getQqCode())
                .stuEmail(studentUpload.getStuEmail())
                .stuAddr(studentUpload.getStuAddr())
                .entryTime(DateUtils.parseDate(studentUpload.getEntryTime(), "yyyy-MM-dd"))
                .graduationSession(studentUpload.getGraduationSession())
                .build();
        return student;
    }

    private Integer toSex(String sex) {
        if ("男".equals(sex)) {
            return 1;
        }
        if ("女".equals(sex)) {
            return 0;
        }
        throw new EKnowException("性别转换错误");
    }

}
