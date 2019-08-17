package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constants.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Permission;
import com.itheima.health.service.PermissionService;
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
@RequestMapping(value = "/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;


    // 从SpringSecurity中获取认证用户的信息
    @RequestMapping(value = "/findAll")
    public Result findAll(){
        try {
            List<Permission> list = permissionService.findAll();
            if(list!=null && list.size()>0){
                return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
            }
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }   catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

}
