package org.mt.mortnon.service;

import org.mt.mortnon.dal.sys.domain.SysUser;

/**
 * hello
 *
 * @date 2021-04-13 17:55:01
 * @author dongfangzan
 */
public interface HelloService {

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
}
