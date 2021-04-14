package org.mt.mortnon.web.vo;

/**
 * 这是一个测试输入类
 */
public class HelloInput {

    /**
     * id
     * @required
     */
    private String id;

    /**
     * 姓名
     * @required
     */
    private String name;

    /**
     * 年龄
     */
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
