package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.constants.MessageConstant;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CheckItemServiceImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2019/8/2 15:55
 * @Version V1.0
 */
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public void add(Role role, Integer[] checkitemIds,Integer[] checkitemIds2) {
        // 1：保存检查组
        roleDao.add(role);
        // 2：使用保存后的检查组的id和检查项的id，向中间表中插入数据
        setCheckGroupAndCheckItem2(role.getId(), checkitemIds,checkitemIds2);
    }


        @Override
        public PageResult findPage(String queryString, Integer currentPage, Integer pageSize) {
            PageHelper.startPage(currentPage,pageSize);
            Page<Role> page = roleDao.findPage(queryString);
            return new PageResult(page.getTotal(),page.getResult());
        }

        @Override
        public Role findById(Integer id) {
            return roleDao.findById(id);
        }

        // 使用检查组的id，查询当前检查组对应检查项的集合
        @Override
        public List<Integer> findCheckItemByCheckGroupId(Integer id) {
            return roleDao.findCheckItemByCheckGroupId(id);
        }

        // 更新检查组
        @Override
        public void update(Role role, Integer[] checkitemIds,Integer[]checkitemIds2) {
            // 1：更新检查组（update语句）
            roleDao.update(role);
            // 2：使用检查组id，删除检查组和检查项的中间表
            roleDao.deleteCheckGroupAndCheckItemByCheckGroupId(role.getId());
            // 3：使用检查组id，删除角色和菜单的中间表
            roleDao.deleteRoleAndMenuByRoleId(role.getId());
            // 4：在重新建立关联关系（已经做过了）
            setCheckGroupAndCheckItem2(role.getId(),checkitemIds,checkitemIds2);

        }

        @Override
        public List<Role> findAll() {
            return roleDao.findAll();
        }

    @Override
    public List<Integer> findCheckItemByMenuId(Integer id) {
        return roleDao.findCheckItemByMenuId(id);
    }

    @Override
    public void deleteById(Integer id) {
       List<Integer> count=roleDao.findUserByRoleId(id);
       if (count!=null&&count.size()>0){
           throw new RuntimeException(MessageConstant.DELETE_ROLE_COUNT_FAIL);
       }else {
           roleDao.deleteRoleAndPermission(id);
           roleDao.deleteRoleAndMenu(id);
           roleDao.deleteRole(id);
       }
    }


    /*//向检查组检查项中间表中插入数据
    private void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkItemId : checkitemIds) {
                // 方案一：传递多个参数，在Dao中使用@Param指定每个参数名称
                //checkGroupDao.addCheckGroupAndCheckItem(checkGroupId,checkItemId);
                // 方案二：传递一个Map<String,Integer>，直接使用key指定名称
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkItemId", checkItemId);
                roleDao.addCheckGroupAndCheckItem(map);
            }
        }
    }*/

    private void setCheckGroupAndCheckItem2(Integer roleId, Integer[] checkitemIds,Integer[]checkitemIds2) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer permissionId : checkitemIds) {
                // 方案一：传递多个参数，在Dao中使用@Param指定每个参数名称
                //checkGroupDao.addCheckGroupAndCheckItem(checkGroupId,checkItemId);
                // 方案二：传递一个Map<String,Integer>，直接使用key指定名称
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("roleId", roleId);
                    map.put("permissionId", permissionId);
                    System.out.println(permissionId);
                    roleDao.addCheckGroupAndCheckItem(map);

            }
        }
        if (checkitemIds2!=null&&checkitemIds2.length>0){
            for (Integer menuId : checkitemIds2) {
                Map<String,Integer>map=new HashMap<>();
                map.put("roleId",roleId);
                map.put("menuId",menuId);
                System.out.println(menuId);
                roleDao.addRoleAndMenu(map);

            }

        }
    }
}

