package com.mxd.service.impl;

import com.mxd.dao.RegisterMapper;
import com.mxd.dao.VersionMapper;
import com.mxd.entity.Register;
import com.mxd.entity.Result;
import com.mxd.entity.Version;
import com.mxd.service.TestService;
import com.mxd.service.Constants;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    RegisterMapper registerMapper;

    @Override
    public Result test(String arg1, String arg2) {
        try {
            if (StringUtils.isNullOrEmpty(arg1)) {
                return new Result(Result.LACK_REGISTER_CODE, "lack registerCode!");
            }
            if (StringUtils.isNullOrEmpty(arg2)) {
                return new Result(Result.LACK_IMEI, "lack imei!");
            }

            Register register = registerMapper.selectByRegisterCode(arg1);
            if (register == null) {
                return new Result(Result.REGISTER_CODE_ERROR, "registerCode is error!");
            } else {
                if ("2".equals(register.getStatus())) {
                    return new Result(Result.LACK_REGISTER_FORBIDDEN, "registerCode is forbidden!");
                }
                if ("".equals(register.getImei())) {
                    register.setImei(arg2);
                    register.setStatus("1");
                    register.setUpdateTime(null);
                    register.setActiveTime(new Date());
                    registerMapper.updateByPrimaryKeySelective(register);
                } else {
                    if (!arg2.equals(register.getImei())) {
                        return new Result(Result.IMEI_ERROR, "imei is error!");
                    } else {
                        register.setActiveTime(new Date());
                        registerMapper.updateByPrimaryKeySelective(register);
                    }
                }
            }
            return new Result(Result.SUCCESS_CODE, "success!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.FAIL_CODE, "unknow error!");
        }
    }
}
