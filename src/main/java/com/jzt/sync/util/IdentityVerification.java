package com.jzt.sync.util;


import java.io.UnsupportedEncodingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class IdentityVerification {
    public static final String TOKEN_KEY = "TOKEN";

    public IdentityVerification() {
    }

    public static HttpServletRequest currentRequest() {
        try {
            return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (Exception var1) {
            return null;
        }
    }

    public static HttpServletResponse currentResponse() {
        try {
            return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
        } catch (Exception var1) {
            return null;
        }
    }

    public static String currentRemoteIp() {
        HttpServletRequest request = currentRequest();
        if (request == null) {
            return "";
        } else {
            return request.getHeader("x-forwarded-for") != null ? request.getHeader("x-forwarded-for") : request.getRemoteAddr();
        }
    }

    public static String currentHost() {
        HttpServletRequest request = currentRequest();
        if (request == null) {
            return "";
        } else {
            String host = request.getHeader("X-Forwarded-Host");
            return !StringUtils.isEmpty(host) ? host : request.getHeader("Host");
        }
    }

    public static String currentUrl() {
        HttpServletRequest request = currentRequest();
        return request == null ? "" : request.getScheme() + "://" + currentHost() + request.getRequestURI();
    }

    public static boolean isEqualPath(String s1, String s2) {
        if (!StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2)) {
            int p1 = s1.indexOf("?");
            if (p1 >= 0) {
                s1 = s1.substring(0, p1);
            }

            int p2 = s2.indexOf("?");
            if (p2 >= 0) {
                s2 = s2.substring(0, p1);
            }

            return s1.toLowerCase().equals(s2.toLowerCase());
        } else {
            return false;
        }
    }

    public static String currentToken() {
        HttpServletRequest request = currentRequest();
        if (request == null) {
            return "";
        } else {
            String token = request.getHeader("TOKEN");
            if (!StringUtils.isEmpty(token)) {
                return token;
            } else {
                token = Conv.NS(request.getAttribute("TOKEN"));
                if (!StringUtils.isEmpty(token)) {
                    return token;
                } else {
                    token = getCookieValue("TOKEN");
                    return !StringUtils.isEmpty(token) ? token : null;
                }
            }
        }
    }

    public static void writeToken(String token) {
        Cookie cookie = new Cookie("TOKEN", token);
        cookie.setPath("/");
        writeCookie(cookie);
    }

    public static boolean isFilterPass(HttpServletRequest httpRequest) {
        String thisUrl = httpRequest.getRequestURI();
        return thisUrl.startsWith("/static/") || thisUrl.startsWith("/manage/") || thisUrl.startsWith("/druid/") || thisUrl.startsWith("/igh2/") || thisUrl.endsWith(".css") || thisUrl.endsWith(".jpg") || thisUrl.endsWith(".jpeg") || thisUrl.endsWith(".html") || thisUrl.endsWith(".js") || thisUrl.endsWith(".png") || thisUrl.endsWith(".gif") || thisUrl.endsWith(".map");
    }

    public static Page parsePagination() {
        HttpServletRequest request = currentRequest();
        HttpParameterParser parser = HttpParameterParser.newInstance(request);
        int pageNo = parser.getIntValue("pageNo", 1);
        int pageSize = parser.getIntValue("pageSize", 10);
        String orderBy = parser.getString("orderBy", "");
        if (pageNo <= 0) {
            pageNo = 1;
        }

        if (pageSize <= 0 || pageSize > 500) {
            pageSize = 20;
        }

        Page pagination = new Page(pageNo, pageSize);
        return pagination;
    }

    public static Cookie[] getCookies() {
        HttpServletRequest request = currentRequest();
        return request == null ? null : request.getCookies();
    }

    public static Cookie getCookie(String name) {
        Cookie[] cookies = getCookies();
        if (cookies != null && cookies.length > 0) {
            for(int i = 0; i < cookies.length; ++i) {
                Cookie cookie = cookies[i];
                String cookName = cookie.getName();
                if (cookName != null && cookName.equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        return cookie == null ? null : cookie.getValue();
    }

    public static void writeCookie(Cookie cookie) {
        if (cookie != null) {
            HttpServletResponse response = currentResponse();
            if (response != null) {
                response.addCookie(cookie);
            }

        }
    }

    public static void removeCookie(String cookieName, String path) {
        HttpServletResponse response = currentResponse();
        HttpServletRequest request = currentRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for(int i = 0; i < cookies.length; ++i) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(cookieName)) {
                    cookie.setMaxAge(0);
                    cookie.setPath(path);
                    response.addCookie(cookie);
                    break;
                }
            }

        }
    }

    public static String urlEncoding(String value) {
        try {
            byte[] bs = Base64.encodeBase64URLSafe(value.getBytes("UTF-8"));
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("encode error.", var2);
        }
    }
}
