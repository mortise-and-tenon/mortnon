package fun.mortnon.mortnon.framework.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author dongfangzan
 * @date 27.4.21 3:41 下午
 */
public class WebUtil {

    private static String UTF8 = "UTF-8";

    private static String JSON_CONTENT_TYPE = "application/json";

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static void printJson(HttpServletResponse response, Object object) throws Exception{
        response.setCharacterEncoding(UTF8);
        response.setContentType(JSON_CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JacksonUtil.objectToJson(object));
        printWriter.flush();
        printWriter.close();
    }
}
