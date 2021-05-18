package fun.mortnon.mortnon.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import fun.mortnon.mortnon.dal.sys.entity.SysUser;
import fun.mortnon.mortnon.dal.sys.mapper.SysUserMapper;
import fun.mortnon.mortnon.service.base.impl.BaseServiceImpl;
import fun.mortnon.mortnon.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dongfangzan
 * @date 28.4.21 3:53 下午
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /** sysUser 仓储*/
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }

        SysUser sysUser = new SysUser().setUsername(username);
        return sysUserMapper.selectOne(new QueryWrapper<>(sysUser));
    }
}