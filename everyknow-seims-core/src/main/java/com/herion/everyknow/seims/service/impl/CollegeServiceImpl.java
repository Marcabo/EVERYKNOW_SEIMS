package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.ClazzDao;
import com.herion.everyknow.seims.dao.CollegeDao;
import com.herion.everyknow.seims.dao.DeptDao;
import com.herion.everyknow.seims.dao.entity.Clazz;
import com.herion.everyknow.seims.dao.entity.College;
import com.herion.everyknow.seims.dao.entity.Dept;
import com.herion.everyknow.seims.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 学院管理 ServiceImpl 层
 * @auther wubo25320
 * @create 2020-03-24 22:11
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public College queryById(Integer id) {
        return collegeDao.queryById(id);
    }

    @Override
    public List<College> queryAll() {
        return collegeDao.queryAll();
    }

    @Override
    public int insert(College college) {
        return collegeDao.insert(college);
    }

    @Override
    public int update(College college) {
        College oldCollege = collegeDao.queryById(college.getId());
        if (!college.getCollegeCode().equals(oldCollege.getCollegeCode())) {
            Dept newDept = new Dept();
            newDept.setCollegeCode(college.getCollegeCode());
            deptDao.updateCollegeCode(newDept, oldCollege.getCollegeCode());

            Clazz newClazz = new Clazz();
            newClazz.setCollegeCode(college.getCollegeCode());
            clazzDao.updateCollegeCode(newClazz, oldCollege.getCollegeCode());
        }
        return collegeDao.update(college);
    }

    @Override
    public int deleteById(Integer id) {
        College college = collegeDao.queryById(id);

        Dept dept = new Dept();
        dept.setCollegeCode(college.getCollegeCode());
        deptDao.delete(dept);

        Clazz clazz =new Clazz();
        clazz.setCollegeCode(college.getCollegeCode());
        clazzDao.delete(clazz);
        return collegeDao.deleteById(id);
    }
}
