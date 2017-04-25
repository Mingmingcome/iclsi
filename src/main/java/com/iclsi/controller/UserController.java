package com.iclsi.controller;

import com.iclsi.entity.User;
import com.iclsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public String register(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        /*
            2017-4-18 19:51:41
            没有考虑注册不成功的情况
         */
        /*
            2017-4-18 19:57:37
            在service层添加了简单的判断
         */
        Map<String, Integer> param = new HashMap<String, Integer>();

        /*
        如果前端发送数据Content-Type:application/x-www-form-urlencoded,可以直接获取通过key可以取得value值
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(user.getEmail());
        System.out.println("0000000");
        */
        int register;
        // 判断非空
        if( user.getEmail() == null || user.getPassword() == null || user.getEmail() == "" || user.getPassword() == "") {
            register = 0;
        }else {
            register = userService.register(user.getEmail(), user.getPassword());
        }

        param.put("register", register);

        String temp = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        return temp;
    }

    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8;")
    public String login(HttpServletRequest request,HttpServletResponse response,@RequestBody User user) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        int login = userService.login(request,user);
        param.put("login",login);

        String temp = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        return temp;
    }
}
