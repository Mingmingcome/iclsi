package com.iclsi.utils;



import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by luhaoming123 on 2017/5/19.
 */
public class Connector {

    public static JSONObject get(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectTmp = new JSONObject();
        try{
            //包装request的输入流
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(request.getInputStream()));
            //缓冲字符
            StringBuffer sb=new StringBuffer("");
            String line;
            while((line=br.readLine())!=null){
                sb.append(line);
            }

            System.out.println(sb);

            br.close();//关闭缓冲流
            jsonObjectTmp = jsonObject.getJSONObject(sb.toString());

        }catch (Exception e){

        }
        return jsonObjectTmp;
    }

    public static void post(HttpServletRequest request, HttpServletResponse response,String result) throws IOException {

        OutputStream os = response.getOutputStream();
        os.write(result.getBytes("utf-8"));
        System.out.println(os);
        os.flush();
        os.close();

    }

}
