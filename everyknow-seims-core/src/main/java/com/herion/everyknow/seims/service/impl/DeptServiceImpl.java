package com.herion.everyknow.seims.service.impl;

import cn.hutool.core.util.StrUtil;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.dao.ClazzDao;
import com.herion.everyknow.seims.dao.DeptDao;
import com.herion.everyknow.seims.dao.entity.Clazz;
import com.herion.everyknow.seims.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.herion.everyknow.seims.dao.entity.Dept;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public Dept queryById(Integer id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }

    @Override
    public List<Dept> queryList(Dept dept) {
        return deptDao.queryList(dept);
    }

    @Override
    public int insert(Dept dept) {
        if (this.queryByCode(dept.getDeptCode()) != null) {
            throw new EKnowException("已存在同 code 的专业");
        }

        if (StrUtil.isBlank(dept.getCollegeCode())) {
            throw new EKnowException("学院代码(collegeCode)不能为空");
        }
        return deptDao.insert(dept);
    }

    @Override
    public int update(Dept dept) {
        Dept oldDept = deptDao.queryById(dept.getId());

        if (!dept.getDeptCode().equals(oldDept.getDeptCode())) {
            if (this.queryByCode(dept.getDeptCode()) != null) {
                throw new EKnowException("已存在同 code 的专业");
            }

            Clazz newClazz = new Clazz();
            newClazz.setDeptCode(dept.getDeptCode());
            clazzDao.updateDeptCode(newClazz, oldDept.getDeptCode());
        }

        if (StrUtil.isBlank(dept.getCollegeCode())) {
            throw new EKnowException("学院代码(collegeCode)不能为空");
        }

        return deptDao.updateById(dept);
    }

    @Override
    public int deleteById(Integer id) {
        Dept dept = deptDao.queryById(id);

        Clazz clazz = new Clazz();
        clazz.setDeptCode(dept.getDeptCode());
        clazzDao.delete(clazz);

        return deptDao.deleteById(id);
    }

    @Override
    public Dept queryByCode(String deptCode) {
        Dept query = new Dept();
        query.setDeptCode(deptCode);
        List<Dept> deptList = deptDao.queryList(query);
        return deptList.size() > 0 ? deptList.get(0) : null;
    }

    @Override
    public List<Dept> queryByName(String deptName) {
        return deptDao.queryByName(deptName);
    }
}
