package com.iclsi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iclsi.entity.Clock;
import com.iclsi.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luhaoming123 on 2017/5/1.
 */
@Controller
@RequestMapping(value = "/clock")
public class ClockController {

    @Autowired
    ClockService clockService;

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
}
