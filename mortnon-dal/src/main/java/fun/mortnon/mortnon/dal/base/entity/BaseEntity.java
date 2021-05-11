package fun.mortnon.mortnon.dal.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * entity基类
 *
 * @author dongfangzan
 * @date 14.4.21 8:17 下午
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 3581463679936614697L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;

    /**
     * 创建时间
     */
    protected Date gmtCreate;

    /**
     * 修改时间
     */
    protected Date gmtModify;

    /**
     * 租户id
     */
    protected String tenantId;
}
