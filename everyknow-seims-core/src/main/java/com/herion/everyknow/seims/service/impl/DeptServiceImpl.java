package com.herion.everyknow.seims.service.impl;

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
        return deptDao.insert(dept);
    }

    @Override
    public int update(Dept dept) {
        Dept oldDept = deptDao.queryById(dept.getId());

        if (!dept.getDeptCode().equals(oldDept.getDeptCode())) {
            Clazz newClazz = new Clazz();
            newClazz.setDeptCode(dept.getDeptCode());
            clazzDao.updateDeptCode(newClazz, oldDept.getDeptCode());
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
}
