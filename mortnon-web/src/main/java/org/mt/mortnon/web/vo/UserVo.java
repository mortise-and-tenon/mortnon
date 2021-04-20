package org.mt.mortnon.web.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 20.4.21 11:19 上午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseVo {

    private static final long serialVersionUID = -4113878586264104760L;

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
