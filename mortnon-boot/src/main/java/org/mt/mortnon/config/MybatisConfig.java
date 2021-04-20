package org.mt.mortnon.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.mt.mortnon.constants.MortnonConstants;
import org.mt.mortnon.constants.MortnonContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis 配置
 *
 * @author dongfangzan
 * @date 20.4.21 10:06 上午
 */
@Configuration
@EnableTransactionManagement
@MapperScan("org.mt.mortnon.**.mapper")
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }


    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 租户拦截器
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                return new StringValue(MortnonContextHolder.getTenantId());
            }

            @Override
            public String getTenantIdColumn() {
                return MortnonConstants.TENANT_ID;
            }

            // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
            @Override
            public boolean ignoreTable(String tableName) {
                return false;
            }
        }));

        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return interceptor;
    }

}
