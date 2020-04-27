package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.StudentDao;
import com.herion.everyknow.seims.dao.bean.StudentAndEmploy;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.dao.mapper.StudentMapper;
import com.herion.everyknow.seims.service.bean.DataVisualRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description 学生基本信息表(Student) DaoImpl
 * @auther wubo25320
 * @create 2020-04-05 16:24
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private StudentMapper mapper;

    @Override
    public IPage<Student> queryPage(Page page, Student student) {
        LambdaQueryWrapper<Student> likeWrapper = this.createLikeWrapper(student);
        return mapper.selectPage(page, likeWrapper);
    }

    @Override
    public IPage<Student> queryNoEmployPage(Page page, Student student) {
        LambdaQueryWrapper<Student> likeWrapper = this.createLikeWrapper(student);
        likeWrapper.notInSql(Student::getStuId, "select stu_id FROM student_employment_info");
        return mapper.selectPage(page, likeWrapper);
    }

    @Override
    public IPage<Student> queryNoFilePage(Page page, Student student) {
        LambdaQueryWrapper<Student> likeWrapper = this.createLikeWrapper(student);
        likeWrapper.notInSql(Student::getStuId, "select stu_id FROM student_file");
        return mapper.selectPage(page, likeWrapper);
    }

    @Override
    public Student queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Student> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<Student> queryAll(Student student) {
        return mapper.selectList(this.createWrapper(student));
    }

    @Override
    public int insert(Student student) {
        return mapper.insert(student);
    }

    @Override
    public int update(Student student) {
        return mapper.updateById(student);
    }

    @Override
    public Boolean checkExist(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Student::getId, student.getId());
        wrapper.eq(Student::getStuId, student.getStuId());
        return mapper.selectList(wrapper).size() > 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insertBatch(List<Student> list) {
        return mapper.insertBatchSomeColumn(list);
    }

    @Override
    public List<StudentAndEmploy> getStudentAndEmploy(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(student.getGraduationSession())) {
            wrapper.eq(Student::getGraduationSession, student.getGraduationSession());
        }
        if (StrUtil.isNotBlank(student.getCollegeCode())) {
            wrapper.eq(Student::getCollegeCode, student.getCollegeCode());
        }
        if (StrUtil.isNotBlank(student.getDeptCode())) {
            wrapper.eq(Student::getDeptCode, student.getDeptCode());
        }
        if (student.getClazzId() != null) {
            wrapper.eq(Student::getClazzId, student.getClazzId());
        }

        return mapper.getStudentAndEmploy(wrapper);
    }

    @Override
    public List<String> getAllGraduationSession() {
        return mapper.getAllGraduationSession();
    }

    private LambdaQueryWrapper<Student> createWrapper(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(student.getStuId())) {
            wrapper.eq(Student::getStuId, student.getStuId());
        }
        return wrapper;
    }

    private LambdaQueryWrapper<Student> createLikeWrapper(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(student.getCollegeCode())) {
            wrapper.eq(Student::getCollegeCode, student.getCollegeCode());
        }
        if (StrUtil.isNotBlank(student.getDeptCode())) {
            wrapper.eq(Student::getDeptCode, student.getDeptCode());
        }
        if (student.getClazzId() != null) {
            wrapper.eq(Student::getClazzId, student.getClazzId());
        }
        if (StrUtil.isNotBlank(student.getStuName())) {
            wrapper.like(Student::getStuName, student.getStuName());
        }
        if (StrUtil.isNotBlank(student.getStuId())) {
            wrapper.like(Student::getStuId, student.getStuId());
        }
        if (student.getIdentificationNumber() != null) {
            wrapper.like(Student::getIdentificationNumber, student.getIdentificationNumber());
        }
        if (student.getEntryTime() != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(student.getEntryTime());
            instance.add(Calendar.YEAR, 1);
            instance.add(Calendar.MILLISECOND, -1);
            wrapper.between(Student::getEntryTime, student.getEntryTime(), instance.getTime());
//            wrapper.eq(Student::getEntryTime, student.getEntryTime());
        }
        if (StrUtil.isNotBlank(student.getGraduationSession())) {
            wrapper.eq(Student::getGraduationSession, student.getGraduationSession());
        }
        return wrapper;
    }
}
