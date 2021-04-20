package org.mt.mortnon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.mt.mortnon.dal.sys.entity.SysUser;

import java.util.List;

/**
 * hello
 *
 * @date 2021-04-13 17:55:01
 * @author dongfangzan
 */
public interface HelloService extends IService<SysUser> {

    /**
     * hello
     *
     * @return 名称
     */
    String hello();

    /**
     * 测试获取用户
     * @return
     */
    SysUser getUser();

    List<SysUser> getUsers();

    /**
     * 创建用户
     * @param sysUser
     * @return
     */
    SysUser saveUser(SysUser sysUser);

    IPage<SysUser> getByPage();
}
