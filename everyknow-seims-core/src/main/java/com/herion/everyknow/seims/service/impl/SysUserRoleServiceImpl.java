package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.SysUserRoleDao;
import com.herion.everyknow.seims.dao.entity.SysUserRole;
import com.herion.everyknow.seims.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色表(SysUserRole)表服务实现类
 *
 * @author wubo25320
 * @since 2020-04-20 14:03:22
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole queryById(Integer id) {
        return this.sysUserRoleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysUserRole sysUserRole) {
        return this.sysUserRoleDao.insert(sysUserRole);
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysUserRole sysUserRole) {
        return this.sysUserRoleDao.update(sysUserRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserRoleDao.deleteById(id) > 0;
    }

    @Override
    public SysUserRole queryByUserId(Integer userId) {
        SysUserRole query = new SysUserRole();
        query.setUserId(userId);
        List<SysUserRole> sysUserRoleList = sysUserRoleDao.queryAll(query);
        if (sysUserRoleList == null || sysUserRoleList.size() == 0) {
            return new SysUserRole();
        }
        return sysUserRoleList.get(0);
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        return sysUserRoleDao.deleteByUserId(userId);
    }
}