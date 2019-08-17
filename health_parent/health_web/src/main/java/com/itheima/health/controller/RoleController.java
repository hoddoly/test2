package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constants.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CheckItemController
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2019/8/2 15:55
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Reference
    RoleService roleService;

    // 新增保存（json）
    @RequestMapping(value = "/add")
    public Result add(@RequestBody Role role, Integer [] checkitemIds,Integer[]checkitemIds2){
        try {
            roleService.add(role,checkitemIds,checkitemIds2);
            return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_ROLE_FALL);
        }
    }

    // 分页查询检查组
    @RequestMapping(value = "/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = roleService.findPage(
                queryPageBean.getQueryString(),
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize());
        return pageResult;
    }

    // 主键查询
    @RequestMapping(value = "/findById")
    public Result findById(Integer id){
        try {
            Role role = roleService.findById(id);
            if(role!=null){
                return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,role);
            }
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    // 使用检查组的id，查询当前检查组对应检查项的集合
    @RequestMapping(value = "/findCheckItemByCheckGroupId")
    public List<Integer> findCheckItemByCheckGroupId(Integer id){
        List<Integer> list = roleService.findCheckItemByCheckGroupId(id);
        return list;
    }

    // 使用检查组的id，查询当前检查组对应检查项的集合
    @RequestMapping(value = "/findCheckItemByMenuId")
    public List<Integer> findCheckItemByMenuId(Integer id){
        List<Integer> list = roleService.findCheckItemByMenuId(id);
        return list;
    }

    // 更新检查组
    @RequestMapping(value = "/update")
    public Result update(@RequestBody Role role,Integer [] checkitemIds,Integer[]checkitemIds2){
        try {
            roleService.update(role,checkitemIds,checkitemIds2);
            return new Result(true,MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ROLE_FAIL);
        }
    }

    // 查询所有
    @RequestMapping(value = "/findAll")
    public Result findAll(){
        try {
            List<Role> list = roleService.findAll();
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    @RequestMapping(value = "/delete")
    public Result delete(Integer id){
        try {
            roleService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_ROLE_SUCCESS);
        }  catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }   catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_ROLE_FAIL);
        }
    }

}
