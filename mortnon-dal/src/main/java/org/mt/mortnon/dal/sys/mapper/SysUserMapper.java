package org.mt.mortnon.dal.sys.mapper;

import org.mt.mortnon.dal.sys.domain.SysUser;

/**
 * @author dongfangzan
 * @date 2021-04-14 20:25:12
 */
public interface SysUserMapper {
    /**
     * 根据id获取参数
     *
     * @param id
     * @return
     */
    SysUser getById(Long id);
}
