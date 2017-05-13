package com.iclsi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iclsi.entity.Clock;
import com.iclsi.entity.User;
import com.iclsi.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luhaoming123 on 2017/5/1.
 */
@Controller
@RequestMapping(value = "/clock")
public class ClockController {

    @Autowired
    ClockService clockService;

    /**
     * 云锁注册
     * @param request
     * @param response
     * @param clock
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public String register(HttpServletRequest request, HttpServletResponse response, @RequestBody Clock clock) {

        Map<String, Integer> param = new HashMap<String, Integer>();
        int register;
        // 判断非空
        if( clock.getClockId() <= 0 || clock.getPassword() == null || clock.getPassword() == "") {
            register = 0;
        }else {
            register = clockService.register(clock.getClockId(), clock.getPassword());
        }

        param.put("register", register);

        String temp = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        return temp;
    }

    /**
     * 查询所有的锁
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAll", produces = "application/json;charset=UTF-8")
    public List<Clock> queryAll() {
        return clockService.queryAll();
    }

    /**
     * 开锁
     * @param clockId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/open", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String openClock(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success",clockService.openClock(clockId, userId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 上锁
     * @param clockId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/close", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String closeClock(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success",clockService.closeClock(clockId, userId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "/passwordModification", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    public String modifyPassword(@RequestBody Clock clock) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success", clockService.modifyPassword(clock));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "/{clockId}/delete", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteClock(@PathVariable("clockId") long clockId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success", clockService.deleteClock(clockId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

}
