package org.mt.mortnon.dal.sys.domain;

import org.mt.mortnon.dal.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 系统用户
 *
 * @author dongfangzan
 * @date 14.4.21 8:21 下午
 */
@Entity
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 6159800674264566616L;

    /**
     * 用户名
     */
    @Column(length = 128)
    private String userName;

    /**
     * 昵称
     */
    @Column(columnDefinition = "varchar(20) COMMENT '昵称'")
    private String nickName;

    /**
     * email
     */
    @Column(length = 128)
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
