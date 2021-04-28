package org.mt.mortnon.service.login.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 27.4.21 3:31 下午
 */
@Data
@Accessors(chain = true)
public class LoginUser {

    private String id;

    private String username;
}
