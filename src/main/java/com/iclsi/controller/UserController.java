package com.iclsi.controller;

import com.alibaba.fastjson.JSONObject;
import com.iclsi.annotation.annotation.Authorization;
import com.iclsi.entity.User;
import com.iclsi.service.UserService;
import com.iclsi.utils.Connector;
import com.iclsi.utils.Constants;
import com.iclsi.utils.JJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
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
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

        System.out.println(user.getEmail());
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
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8;")
    public String login(HttpServletRequest request,HttpServletResponse response,@RequestBody User user) {
        Map<String, Object> param = new HashMap<String, Object>();
        int login = userService.login(request,user);
        param.put("login",login);

        // 登录成功之后创建token
        if(login == 1) {
            Map<String , Object> payload=new HashMap<String, Object>();
            Date date=new Date();
            JJwt jJwt = new JJwt();
            payload.put("uid", request.getSession().getAttribute("userId"));//用户ID
            payload.put("iat", date.getTime());//生成时间
            payload.put("ext",date.getTime()+1000*60*60*24);//过期时间1小时
            String token=jJwt.createToken(payload);
            System.out.println("生成的token:" + token);

            param.put("token", token);
        }

        // 登录成功跳转的URL
        param.put("url", Constants.loginSuccessURL);

        // 登录成功返回userId
        param.put("userId", request.getSession().getAttribute("userId"));

        String temp = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        return temp;
    }
}
