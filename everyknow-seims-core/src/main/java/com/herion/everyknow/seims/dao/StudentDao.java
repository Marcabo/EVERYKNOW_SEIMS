package com.herion.everyknow.seims.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.bean.StudentAndEmploy;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.bean.DataVisualRequest;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 学生基本信息表(Student)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-23 23:47:10
 */
public interface StudentDao {

    /**
     * 根据条件分页查询学生列表
     * @param page
     * @param student
     * @return
     */
    IPage<Student> queryPage(Page page, Student student);

    /**
     * 根据条件分页查询未就业学生列表
     * @param page
     * @param student
     * @return
     */
    IPage<Student> queryNoEmployPage(Page page, Student student);

    /**
     * 根据条件分页查询未登记档案学生列表
     * @param page
     * @param student
     * @return
     */
    IPage<Student> queryNoFilePage(Page page, Student student);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Student queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Student> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param student 实例对象
     * @return 对象列表
     */
    List<Student> queryAll(Student student);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int update(Student student);

    /**
     * 检查学生是否存在
     * <br />
     * id 不等于 且 stuId 等于 返回 true
     * @param student
     * @return
     */
    Boolean checkExist(Student student);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(List<Student> list);

    /**
     * 学生表 和 学生就业信息表 联查(一对一)
     * @param student
     * @return
     */
    List<StudentAndEmploy> getStudentAndEmploy(Student student);

    /**
     * 获取当前数据库中存在的所有毕业届数
     * @return
     */
    List<String> getAllGraduationSession();

}