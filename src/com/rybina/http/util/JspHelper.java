package com.rybina.http.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static String JSP_FORMAT = "/WEB-INF/private/%s.jsp";

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}
