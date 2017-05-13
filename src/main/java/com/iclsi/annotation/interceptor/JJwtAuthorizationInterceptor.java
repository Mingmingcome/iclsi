package com.iclsi.annotation.interceptor;

import com.iclsi.utils.JJwt;
import com.iclsi.utils.TokenState;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by luhaoming123 on 2017/5/5.
 */
public class JJwtAuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 有一个bug待解决：就是通过直接访问URL可以访问到静态资源

        //其他API接口一律校验token
        System.out.println("开始校验token");
        //从请求头中获取token
        String token=request.getHeader("token");
        JJwt jJwt = new JJwt();
        Map<String, Object> resultMap=jJwt.validToken(token);
        TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
        switch (state) {
            case VALID:
                //取出payload中数据,放入到request作用域中
                request.setAttribute("data", resultMap.get("data"));
                return true;
            case EXPIRED:
            case INVALID:
                System.out.println("无效token");
                //token过期或者无效，则输出错误信息返回给ajax
                JSONObject outputMSg=new JSONObject();
                outputMSg.put("success", false);
                outputMSg.put("msg", "您的token不合法或者过期了，请重新登陆");
                output(outputMSg.toJSONString(), response);
                break;
        }
        return false;
    }

    public void output(String jsonStr,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8;");
        PrintWriter out = response.getWriter();
		out.println();
        out.write(jsonStr);
        out.flush();
        out.close();

    }

}
