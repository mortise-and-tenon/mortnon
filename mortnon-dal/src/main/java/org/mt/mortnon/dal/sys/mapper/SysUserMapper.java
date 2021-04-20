package org.mt.mortnon.dal.sys.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mt.mortnon.dal.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongfangzan
 * @date 2021-04-14 20:25:12
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取所有用户
     *
     * @return
     */
    @Select("select u.id, u.user_name, u.nick_name, u.email, u.gmt_create, u.gmt_modify from sys_user u ")
    List<SysUser> getUsers();

    /**
     * 分页查询
     * @param page
     * @return
     */
    @InterceptorIgnore
    IPage<SysUser> selectByPage(@Param("page") Page<?> page);
}
