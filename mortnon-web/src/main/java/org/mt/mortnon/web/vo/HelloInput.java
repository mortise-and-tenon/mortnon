package org.mt.mortnon.web.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 这是一个测试输入类
 *
 * @date 2021-04-13 12:02:23
 * @author dongfangzan
 */
public class HelloInput {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private String id;

    /**
     * 姓名
     * @required
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
