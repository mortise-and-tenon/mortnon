package org.mt.mortnon.dal.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.mt.mortnon.dal.base.BaseEntity;

/**
 * 系统用户
 *
 * @author dongfangzan
 * @date 14.4.21 8:21 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 6159800674264566616L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * email
     */
    private String email;

    /**
     * 性别
     */
    private String sex;
}
