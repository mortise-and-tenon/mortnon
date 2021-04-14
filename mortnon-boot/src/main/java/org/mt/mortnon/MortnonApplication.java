package org.mt.mortnon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动入口
 *
 * @date 2021-04-13 17:32:23
 * @author dongfangzan
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MortnonApplication {
    public static void main(String[] args) {
        SpringApplication.run(MortnonApplication.class, args);
    }
}
