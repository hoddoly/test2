package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.Role;

import java.util.List;

public interface RoleService {

    void add(Role role, Integer[] checkitemIds,Integer[]checkitemIds2);

    PageResult findPage(String queryString, Integer currentPage, Integer pageSize);

    Role findById(Integer id);

    List<Integer> findCheckItemByCheckGroupId(Integer id);

    void update(Role role, Integer[] checkitemIds,Integer[]checkitemIds2);

    List<Role> findAll();

    List<Integer> findCheckItemByMenuId(Integer id);

    void deleteById(Integer id);
}
