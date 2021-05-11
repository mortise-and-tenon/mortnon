package fun.mortnon.mortnon.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * vo 基类
 *
 * @author dongfangzan
 * @date 20.4.21 11:20 上午
 */
@Data
@Accessors(chain = true)
public class BaseVo implements Serializable {

    private static final long serialVersionUID = 1108229809473993403L;

    private Long id;
}
