package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName CheckItemDao
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2019/8/2 15:53
 * @Version V1.0
 */
@Repository
public interface RoleDao {

    // 使用用户id，查询用户对应的角色集合
    public Set<Role> findRoleListByUserId(Integer userId);

    void add(Role role);

    void addCheckGroupAndCheckItem(Map<String, Integer> map);

    Page<Role> findPage(String queryString);

    Role findById(Integer id);

    List<Integer> findCheckItemByCheckGroupId(Integer id);

    void update(Role role);

    void deleteCheckGroupAndCheckItemByCheckGroupId(Integer id);

    List<Role> findAll();


    List<Integer> findCheckItemByMenuId(Integer id);

    void addRoleAndMenu(Map<String, Integer> map);

    void deleteRoleAndMenuByRoleId(Integer id);

    List<Integer> findUserByRoleId(Integer id);

    void deleteRoleAndPermission(Integer id);

    void deleteRoleAndMenu(Integer id);

    void deleteRole(Integer id);
}
