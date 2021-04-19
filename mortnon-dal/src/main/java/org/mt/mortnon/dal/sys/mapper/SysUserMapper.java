package org.mt.mortnon.dal.sys.mapper;

import org.apache.ibatis.annotations.Select;
import org.mt.mortnon.dal.sys.domain.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongfangzan
 * @date 2021-04-14 20:25:12
 */
@Repository
public interface SysUserMapper {
    /**
     * 根据id获取参数
     *
     * @param id
     * @return
     */
    SysUser getById(Long id);

    /**
     * 获取所有用户
     * @return
     */
    @Select("select u.id, u.user_name, u.nick_name, u.email, u.gmt_create, u.gmt_modify from sys_user u ")
    List<SysUser> getUsers();
}
