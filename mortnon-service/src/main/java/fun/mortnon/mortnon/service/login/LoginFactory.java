package fun.mortnon.mortnon.service.login;

import fun.mortnon.mortnon.framework.properties.JwtProperties;
import fun.mortnon.mortnon.service.login.enums.LoginStorageType;
import fun.mortnon.mortnon.service.login.enums.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dongfangzan
 * @date 27.4.21 3:09 下午
 */
@Service
public class LoginFactory {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private final Map<String, LoginService> loginServiceMap = new ConcurrentHashMap<>();

    @Autowired
    private final Map<String, LoginStorageService> loginStorageServiceMap = new ConcurrentHashMap<>();

    /**
     * 根据登录类型获取登录服务
     *
     * @param loginType 登录类型
     * @return          登录服务
     */
    public LoginService getLoginService(LoginType loginType) {
        return loginServiceMap.get(loginType.getCode());
    }

    /**
     * 根据类型获取登录存储服务
     *
     * @param loginStorageType 登录存储类型
     * @return                 登录存储服务
     */
    public LoginStorageService getLoginStorageService(LoginStorageType loginStorageType) {
        return loginStorageServiceMap.get(loginStorageType.getCode());
    }


    /**
     * 获取当前系统配置的登录存储服务
     *
     * @return 登录存储服务
     */
    public LoginStorageService getConfigLoginStorageService() {
        String loginStorageType = jwtProperties.getLoginStorageType();
        return loginStorageServiceMap.get(LoginStorageType.getByType(loginStorageType).getCode());
    }
}
