package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.SysRoleDao;
import com.herion.everyknow.seims.dao.entity.SysRole;
import com.herion.everyknow.seims.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author wubo25320
 * @since 2020-04-20 16:24:13
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Integer id) {
        SysRole sysRole = this.sysRoleDao.queryById(id);
        return sysRole != null ? sysRole : new SysRole();
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysRole sysRole) {
        return this.sysRoleDao.insert(sysRole);
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysRole sysRole) {
        return this.sysRoleDao.update(sysRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysRoleDao.deleteById(id) > 0;
    }

    @Override
    public SysRole queryByRoleName(String roleName) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        return sysRoleDao.queryAll(sysRole).get(0);
    }
}