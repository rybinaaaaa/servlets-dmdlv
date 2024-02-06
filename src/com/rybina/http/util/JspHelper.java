package com.rybina.http.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static String JSP_FORMAT = "/WEB-INF/private/content.jsp";

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}
