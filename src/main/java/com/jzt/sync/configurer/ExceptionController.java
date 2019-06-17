package com.jzt.sync.configurer;

import com.jzt.sync.core.Result;
import com.jzt.sync.core.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author libiao
 * @create 2019/2/21 13:40
 * @description
 */
@RestController
@RestControllerAdvice
@Slf4j
public class ExceptionController {


    public static Object PROCESS_ERROR(HttpServletRequest request, Exception ex) {
        Map<String, Object> r = new LinkedHashMap<>();
        String msg;
        if (ex instanceof ServiceException) {
            //手动抛出的异常
            ServiceException mdtex = ((ServiceException) ex);
            r.put("errorTrace", mdtex.getCause());
            r.put("errorName", mdtex.getMessage());
            return Result.newFail(ex.getMessage()).putData(r);

        } else {
            //其他异常
            log.error(ex.toString(), ex);
            ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse().setStatus(500);
            Enumeration<String> enuma = request.getHeaderNames();
            Map<String, Object> vl = new LinkedHashMap<>();
            while (enuma.hasMoreElements()) {
                String key = enuma.nextElement();
                vl.put(key, request.getHeader(key));
            }

            msg = "发生异常:" + ex.getMessage();

            String trace = "";
            StringWriter sw = null;
            PrintWriter pw = null;
            try {
                sw = new StringWriter();
                pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                trace = sw.toString();
            } finally {
                IOUtils.closeQuietly(pw);
                IOUtils.closeQuietly(sw);
            }

            r.put("errorTrace", trace);
            r.put("errorMsg", msg);
            r.put("ServerName", request.getServerName());
            r.put("Host", request.getHeader("Host"));
            r.put("RemoteIP", currentRemoteIp(request));
            r.put("url", request.getScheme() + "://" + request.getHeader("Host") + request.getRequestURI());
        }

        if (request.getRequestURI().endsWith(".json")) {
            return Result.newFail(msg).putData(r);
        }
        return r;
    }

    @ExceptionHandler(Exception.class)
    public Object handleError(HttpServletRequest request, Exception ex) {
        Object errorData = PROCESS_ERROR(request, ex);
        return errorData;
    }

    private static String currentRemoteIp(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        if (request.getHeader("x-forwarded-for") != null) {
            return request.getHeader("x-forwarded-for");
        }
        return request.getRemoteAddr();
    }
}
