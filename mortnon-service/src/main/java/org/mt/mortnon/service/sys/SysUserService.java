package org.mt.mortnon.service.sys;

import org.mt.mortnon.dal.sys.entity.SysUser;
import org.mt.mortnon.service.base.BaseService;

/**
 * @author dongfangzan
 * @date 28.4.21 3:52 下午
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据账户名获取用户信息
     *
     * @param username 账户名
     * @return         用户信息
     */
    SysUser getUserByUsername(String username);
}
