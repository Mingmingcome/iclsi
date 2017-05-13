package com.iclsi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iclsi.entity.ClockRecord;
import com.iclsi.service.ClockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 查询所有的开关锁记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{clockId}/clockRecord", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<ClockRecord> queryClockRecordByClockId(@PathVariable("clockId") long clockId) {
        return clockRecordService.queryClockRecordByClockId(clockId);
    }

    @ResponseBody
    @RequestMapping(value = "/{clockId}/delete1", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteByClockId(@PathVariable("clockId") long clockId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success", clockRecordService.deleteByClockId(clockId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "/{clockRecordId}/delete2", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public String deleteByClockRecordId(@PathVariable("clockRecordId") long clockRecordId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("success", clockRecordService.deleteByClockRecordId(clockRecordId));
        return JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
    }

}
