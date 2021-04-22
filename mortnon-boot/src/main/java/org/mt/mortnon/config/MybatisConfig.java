package org.mt.mortnon.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.collections.CollectionUtils;
import org.mt.mortnon.framework.properties.MortnonProperties;
import org.mt.mortnon.framework.web.MortnonContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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

    /** 基本配置 */
    @Autowired
    private MortnonProperties mortnonProperties;

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

        // 开启多租户开关
        if (mortnonProperties.isEnableMultiTenant()) {
            // 租户拦截器
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
                @Override
                public Expression getTenantId() {
                    return new StringValue(MortnonContextHolder.getTenantId());
                }

                @Override
                public String getTenantIdColumn() {
                    return mortnonProperties.getTenantColumnName();
                }

                // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
                @Override
                public boolean ignoreTable(String tableName) {
                    if (CollectionUtils.isEmpty(mortnonProperties.getTenantBlackList())) {
                        return false;
                    }

                    return mortnonProperties.getTenantBlackList().contains(tableName);
                }
            }));
        }

        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return interceptor;
    }

}
