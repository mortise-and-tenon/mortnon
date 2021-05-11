package fun.mortnon.mortnon.service.base.impl;

/**
 * 公共服务实现类
 *
 * @author dongfangzan
 * @date 28.4.21 3:51 下午
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.mortnon.mortnon.service.base.BaseService;

/**
 * 公共服务实现类
 *
 * @author dongfangzan
 * @date 20.4.21 2:38 下午
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
