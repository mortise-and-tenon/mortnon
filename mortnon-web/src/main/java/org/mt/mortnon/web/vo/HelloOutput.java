package org.mt.mortnon.web.vo;

import lombok.Data;

/**
 * 这是一个测试用的输出
 * @author zhangsiyuan
 */
@Data
public class HelloOutput {

    /**
     * 姓名
     */
    private String name;

    /**
     * 欢迎语
     */
    private String hello;

    private Long id;
}
