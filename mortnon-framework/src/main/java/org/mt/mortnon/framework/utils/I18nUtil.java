package org.mt.mortnon.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 国际化工具
 *
 * @author dongfangzan
 * @date 20.4.21 11:16 上午
 */
@Component
@Slf4j
public class I18nUtil {

    /** spring管理的messageSource */
    @Autowired
    private MessageSource messageSource;

    /** 静态messageSource */
    private static MessageSource i18nMessage;

    @PostConstruct
    public void init() {
        i18nMessage = messageSource;
    }

    /**
     * 获取国际化信息
     *
     * @param msg
     * @param objects
     * @return
     */
    public static String getMessage(String msg, Object[] objects) {
        String result = msg;
        try {
            result = i18nMessage.getMessage(msg, objects, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            log.error("未配置国际化信息:{}, locale:{}", msg, LocaleContextHolder.getLocale().toLanguageTag());
        }
        return result;
    }

    /**
     * 获取国际化信息
     *
     * @param msg 消息
     * @return    国际化消息
     */
    public static String getMessage(String msg) {
        return getMessage(msg, null);
    }
}
