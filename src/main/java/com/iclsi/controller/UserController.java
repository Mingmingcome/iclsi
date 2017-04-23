package com.iclsi.controller;

import com.iclsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luhaoming123 on 2017/4/17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        /*
            2017-4-18 19:51:41
            没有考虑注册不成功的情况
         */
        /*
            2017-4-18 19:57:37
            在service层添加了简单的判断
         */
        Map<String, Integer> param = new HashMap<String, Integer>();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, Object> param1 = super.getParamsObject(request);
        System.out.println(param1);
        System.out.println("0000");
        int success = userService.register(email,password);
        param.put("success", success);

        String temp = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        return temp;
    }

}
