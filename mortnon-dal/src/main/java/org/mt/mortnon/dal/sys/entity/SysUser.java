package org.mt.mortnon.dal.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.mt.mortnon.dal.base.entity.LogicDeleteEntity;

import javax.validation.constraints.NotNull;

/**
 * @author dongfangzan
 * @date 28.4.21 3:37 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends LogicDeleteEntity {

    /** 用户名 */
    @NotNull
    private String username;

    /** 昵称 */
    private String nickname;

    /** 密码 */
    private String password;

    /** 盐 */
    private String salt;

    /** email */
    private String email;

    /** 手机号码*/
    private String phone;

    /** 头像*/
    private String head;

    /** 性别 */
    private int gender;
}
