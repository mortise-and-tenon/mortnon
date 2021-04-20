package org.mt.mortnon.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mt.mortnon.service.BaseService;

/**
 * 公共服务实现类
 *
 * @author dongfangzan
 * @date 20.4.21 2:38 下午
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
