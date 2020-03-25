package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.Clazz;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface ClazzService{


    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    Clazz queryById(Integer id);

    /**
     * 查询全部
     * @return
     */
    List<Clazz> queryAll();

    /**
     * 条件查询
     * @param clazz
     * @return
     */
    List<Clazz> query(Clazz clazz);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Clazz record);

    /**
     * 根据 id 更新
     * @param record
     * @return
     */
    int update(Clazz record);

}
