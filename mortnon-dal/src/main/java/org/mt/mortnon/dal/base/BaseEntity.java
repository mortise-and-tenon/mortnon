package org.mt.mortnon.dal.base;

import java.io.Serializable;
import java.util.Date;

/**
 * entity基类
 *
 * @author dongfangzan
 * @date 14.4.21 8:17 下午
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 3581463679936614697L;

    /**
     * 主键
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected Date gmtCreate;

    /**
     * 修改时间
     */
    protected Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
