package fun.mortnon.mortnon.dal.base.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author dongfangzan
 * @date 28.4.21 3:47 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LogicDeleteEntity extends BaseEntity {

    /** 逻辑删除位 */
    @TableLogic
    protected int deleted;

    /** 删除时间 */
    protected Date gmtDelete;
}
