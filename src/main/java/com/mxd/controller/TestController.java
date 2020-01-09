package com.mxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.mxd.entity.Result;
import com.mxd.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
@RequestMapping(value = "/test")
public class TestController {
    @Resource
    private TestService testService;
    private static Logger logger = Logger.getLogger("record");

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseBody
    public String auth(@RequestBody String body) {
        String uid = UUID.randomUUID().toString();
        logger.info(uid + ":收到请求/register:" + body);
        JSONObject params = JSONObject.parseObject(body);
        Result result;
        result = testService.test(params.getString("registerCode"), params.getString("imei"));
        logger.info(uid + ":响应请求/register:" + result.toString());
        return result.toString();
    }

}
