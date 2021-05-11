package fun.mortnon.mortnon.web.vo.login;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.NotNull;

/**
 * @author dongfangzan
 * @date 27.4.21 5:11 下午
 */
@Data
@Accessors(chain = true)
public class PasswordLoginInput {

    /**
     * 用户名
     *
     * @mock admin
     */
    @NotNull
    @Length(min = 4, max = 128)
    private String username;

    /**
     * 密码
     *
     * @mock 123456
     */
    @NotNull
    @Length(min = 4, max = 128)
    private String password;

    /**
     * 验证码token
     */
    private String verifyToken;

    /**
     * 验证码
     */
    private String code;

}
