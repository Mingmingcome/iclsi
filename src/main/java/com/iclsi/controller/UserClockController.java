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
     * 通过userId查询用户拥有的云锁
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
     * 通过clockId查询相关联的用户
     *
     * @param clockId
     * @return
     * @method GET
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/userList", method = RequestMethod.GET)
    public List<User> queryUserByClockId(@PathVariable("clockId") long clockId) {
        return userClockService.queryUserByClockId(clockId);
    }

    /**
     * 添加云锁用户
     *
     * @param userClock
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/put", produces = "application/json;charset=utf-8;")
    public String addUserClock(@RequestBody UserClock userClock) {
        Map<String, Object> param = new HashMap<String, Object>();
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
    @RequestMapping(value = "/authority", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    public String authority(@RequestBody UserClock userClock) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success", userClockService.modifyAuthority(userClock));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除云锁用户
     *
     * @param clockId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/delete", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteUserClock(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success", userClockService.deleteUserClock(clockId, userId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }
}



