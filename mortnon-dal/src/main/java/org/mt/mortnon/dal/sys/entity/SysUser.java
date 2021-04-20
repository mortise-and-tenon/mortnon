package org.mt.mortnon.dal.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.mt.mortnon.dal.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 系统用户
 *
 * @author dongfangzan
 * @date 14.4.21 8:21 下午
 */
@Entity
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Table(indexes = {@Index(name = "idx_tenantId", columnList = "id, tenant_id", unique = true)})
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 6159800674264566616L;

    /**
     * 用户名
     */
    @Column(nullable = false, length = 128)
    private String userName;

    /**
     * 昵称
     */
    @Column(columnDefinition = "varchar(20) COMMENT '昵称'")
    private String nickName;

    /**
     * email
     */
    @Column(columnDefinition = "varchar(128) COMMENT 'email'")
    private String email;

    /**
     * 性别
     */
    @Column(columnDefinition = "varchar(1) COMMENT '性别'")
    private String sex;
}
