package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.StudentFileDao;
import com.herion.everyknow.seims.dao.entity.StudentFile;
import com.herion.everyknow.seims.dao.mapper.StudentFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 学生档案信息 Dao 层
 * @auther wubo25320
 * @create 2020-04-15 15:02
 */
@Repository
public class StudentFileDaoImpl implements StudentFileDao {

    @Autowired
    private StudentFileMapper mapper;

    @Override
    public StudentFile queryById(Integer id) {
        return null;
    }

    @Override
    public List<StudentFile> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<StudentFile> queryAll(StudentFile studentFile) {
        List<StudentFile> studentFileList = mapper.selectList(this.createWrapper(studentFile));
        return studentFileList;
    }

    @Override
    public int insert(StudentFile studentFile) {
        return mapper.insert(studentFile);
    }

    @Override
    public int update(StudentFile studentFile) {
        return mapper.updateById(studentFile);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    private LambdaQueryWrapper<StudentFile> createWrapper(StudentFile studentFile) {
        LambdaQueryWrapper<StudentFile> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(studentFile.getStuId())) {
            wrapper.eq(StudentFile::getStuId, studentFile.getStuId());
        }
        return wrapper;
    }
}
