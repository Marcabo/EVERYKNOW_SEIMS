package com.herion.everyknow.seims.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.herion.everyknow.seims.dao.bean.StudentAndEmploy;
import com.herion.everyknow.seims.dao.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-04-14 10:01
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    int insertBatchSomeColumn(List<Student> entityList);

    @Select("SELECT student.*, " +
            "student_employment_info.company_full_name, student_employment_info.company_nature_code, student_employment_info.company_province, student_employment_info.industry_type, student_employment_info.salary, student_employment_info.employ_method " +
            "from student left join student_employment_info on student.stu_id = student_employment_info.stu_id ${ew.customSqlSegment}")
    List<StudentAndEmploy> getStudentAndEmploy(@Param(Constants.WRAPPER) Wrapper<Student> wrapper);

    @Select("SELECT distinct graduation_session from student ")
    List<String> getAllGraduationSession();
}