package org.mt.mortnon.service.impl;

import org.mt.mortnon.dal.sys.domain.SysUser;
import org.mt.mortnon.dal.sys.mapper.SysUserMapper;
import org.mt.mortnon.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * hello
 *
 * @date 2021-04-13 17:56:12
 * @author dongfangzan
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private SysUserMapper sysUserMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello() {
        LOGGER.info("测试service层日志打印");

        LOGGER.error("错误日志测试打印");

        return "Mortnon";
    }

    @Override
    public SysUser getUser() {
        return sysUserMapper.getById(1L);
    }

    @Override
    public List<SysUser> getUsers(){
        return sysUserMapper.getUsers();
    }
}
