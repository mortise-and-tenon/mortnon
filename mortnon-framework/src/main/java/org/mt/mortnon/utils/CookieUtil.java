package org.mt.mortnon.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie 工具
 *
 * @author dongfangzan
 * @date 20.4.21 5:53 下午
 */
public class CookieUtil {

    /**
     * 添加cookie
     *
     * @param response 响应
     * @param name      cookie key
     * @param value    cookie value
     * @param maxAge   超时时间
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     *
     * @param response 响应
     * @param name     cookie name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * 获取cookie值
     *
     * @param request 请求
     * @param name    cookie name
     * @return        cookie value
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        // 这里一定要判空，不然可能导致大量报错影响页面性能
        if (null == cookies) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
