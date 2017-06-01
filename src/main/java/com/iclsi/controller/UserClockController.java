package com.iclsi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iclsi.entity.Clock;
import com.iclsi.entity.User;
import com.iclsi.entity.UserClock;
import com.iclsi.service.UserClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luhaoming123 on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/userClock")
public class UserClockController {

    @Autowired
    private UserClockService userClockService;

    /**
     * 通过userId查询用户相关联的全部云锁
     *
     * @param userId
     * @return
     * @method GET
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/clockList", method = RequestMethod.GET)
    public List<Clock> queryClockByUserId(@PathVariable("userId") long userId) {
        return userClockService.queryClockByUserId(userId);
    }

    /**
     * 通过userId查询用户拥有某权限的云锁
     *
     * @param userId
     * @return
     * @method GET
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/{authority}/clockList", method = RequestMethod.GET)
    public List<Clock> queryClockByUserIdAndAuthority(@PathVariable("userId") long userId,@PathVariable("authority") byte authority) {

        return userClockService.queryClockByUserIdAndAuthority(userId, authority);
    }

    /**
     * 通过clockId查询相关联的全部用户
     *
     * @param clockId
     * @return
     * @method GET
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/userList", method = RequestMethod.GET)
    public List<User> queryUserByClockId(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {

        ArrayList param = new ArrayList();
        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, clockId)) {
            param.add("权限不足，请及时充值！");
            return param;
        }

        return userClockService.queryUserByClockId(clockId);
    }

    /**
     * 通过clockId查询相关联的高级用户
     * @param clockId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/vipUserList", method = RequestMethod.GET)
    public List<User> queryVIPUserByClockId(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {

        ArrayList param = new ArrayList();
        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, clockId)) {
            param.add("权限不足，请及时充值！");
            return param;
        }

        return userClockService.queryVIPUserByClockId(clockId);
    }

    /**
     * 通过clockId查询相关联的普通用户
     * @param clockId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/normalUserList", method = RequestMethod.GET)
    public List<User> queryNormalUserByClockId(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {

        ArrayList param = new ArrayList();
        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, clockId)) {
            param.add("权限不足，请及时充值！");
            return param;
        }

        return userClockService.queryNormalUserByClockId(clockId);
    }

    /**
     * 申请成为云锁用户
     *
     * @param userClock
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/request", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    public String requestUserClock(@RequestBody UserClock userClock) {
        Map<String, Object> param = new HashMap<String, Object>();

        userClock.setAuthority((byte)2);
        param.put("success", userClockService.addUserClock(userClock));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 添加云锁用户
     *
     * @param userClock
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/put", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    public String addUserClock(@RequestBody UserClock userClock, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();

        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, userClock.getClockId())) {
            param.put("success","权限不足，请及时充值！");
            return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        }

        userClock.setAuthority((byte)1);
        param.put("success", userClockService.addUserClock(userClock));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改云锁用户权限
     *
     * @param userClock
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/authority", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    public String authority(@RequestBody UserClock userClock, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();

        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, userClock.getClockId())) {
            param.put("success","权限不足，请及时充值！");
            return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        }

        param.put("success", userClockService.modifyAuthority(userClock));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除云锁用户
     *
     * @param userClock
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteUserClock(@RequestBody UserClock userClock, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();

        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, userClock.getClockId())) {
            param.put("success","权限不足，请及时充值！");
            return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        }

        param.put("success", userClockService.deleteUserClock(userClock.getClockId(), userClock.getUserId()));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }
}



