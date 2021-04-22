package org.mt.mortnon.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 这是一个测试输入类
 *
 * @date 2021-04-13 12:02:23
 * @author dongfangzan
 */
@Data
@Accessors(chain = true)
public class DemoInput {

    /**
     * id
     * @mock 1
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 姓名
     * @mock 东方赞
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     * @mock 12
     */
    @NotNull
    @Range(min = 0, max = 120, message = "年龄必须在0到120之间")
    private int age;
}
