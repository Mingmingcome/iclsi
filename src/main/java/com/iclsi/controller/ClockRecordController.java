package com.iclsi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iclsi.entity.ClockRecord;
import com.iclsi.service.ClockRecordService;
import com.iclsi.service.UserClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luhaoming123 on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/clockRecord")
public class ClockRecordController {

    @Autowired
    private ClockRecordService clockRecordService;

    @Autowired
    private UserClockService userClockService;

    /**
     * 查询所有的开关锁记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/clockRecord", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<ClockRecord> queryClockRecordByClockId(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {
        ArrayList param = new ArrayList();
        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, clockId)) {
            param.add("权限不足，请及时充值！");
            return param;
        }

        return clockRecordService.queryClockRecordByClockId(clockId);
    }

    @ResponseBody
    @RequestMapping(value = "/{clockId}/{userId}/delete1", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteByClockId(@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();

        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, clockId)) {
            param.put("success","权限不足，请及时充值！");
            return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        }

        param.put("success", clockRecordService.deleteByClockId(clockId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "/{clockRecordId}/{clockId}/{userId}/delete2", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteByClockRecordId(@PathVariable("clockRecordId") long clockRecordId,@PathVariable("clockId") long clockId, @PathVariable("userId") long userId) {
        Map<String, Object> param = new HashMap<String, Object>();

        // 验证云锁权限
        if(!userClockService.AuthorityWithAdmin(userId, clockId)) {
            param.put("success","权限不足，请及时充值！");
            return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        }

        param.put("success", clockRecordService.deleteByClockRecordId(clockRecordId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

}
