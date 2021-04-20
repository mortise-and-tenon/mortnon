package org.mt.mortnon.dal.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * entity基类
 *
 * @author dongfangzan
 * @date 14.4.21 8:17 下午
 */
@MappedSuperclass
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 3581463679936614697L;

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    protected Long id;

    /**
     * 创建时间
     */
    @Column(nullable = false, columnDefinition = "timestamp(6) COMMENT '创建时间' DEFAULT CURRENT_TIMESTAMP(6)")
    protected Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(nullable = false, columnDefinition = "timestamp(6) COMMENT '修改时间' DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    protected Date gmtModify;

    /**
     * 租户id
     */
    @Column(name = "tenant_id", nullable = false, columnDefinition = "varchar(20) COMMENT '租户id'")
    protected String tenantId;
}
