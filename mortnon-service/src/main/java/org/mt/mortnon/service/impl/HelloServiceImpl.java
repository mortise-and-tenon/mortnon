package org.mt.mortnon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.mt.mortnon.constants.MortnonContextHolder;
import org.mt.mortnon.dal.sys.entity.SysUser;
import org.mt.mortnon.dal.sys.mapper.SysUserMapper;
import org.mt.mortnon.service.HelloService;
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
@Slf4j
public class HelloServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements HelloService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String hello() {
        log.info("测试service层日志打印");

        log.error("错误日志测试打印");

        return "Mortnon";
    }

    @Override
    public SysUser getUser() {
        return sysUserMapper.selectById(1);
    }

    @Override
    public List<SysUser> getUsers(){
        log.info("当前租户id为{}", MortnonContextHolder.getTenantId());

        return sysUserMapper.selectList(null);
    }

    @Override
    public SysUser saveUser(SysUser sysUser) {
        log.info("当前租户id为{}", MortnonContextHolder.getTenantId());

        super.save(sysUser);
        return sysUser;
    }

    @Override
    public IPage<SysUser> getByPage() {
//        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        return sysUserMapper.selectByPage(new Page<>());
    }
}
