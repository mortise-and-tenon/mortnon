package fun.mortnon.mortnon.service.sys;

import fun.mortnon.mortnon.dal.sys.entity.SysUser;
import fun.mortnon.mortnon.service.base.BaseService;

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
