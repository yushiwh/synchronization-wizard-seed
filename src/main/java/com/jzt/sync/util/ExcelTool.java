package com.jzt.sync.util;

import cn.hutool.poi.excel.ExcelWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author libiao
 * @create 2019-03-18 16:41
 * @description
 */
public class ExcelTool {

    public static void exportToExcel(HttpServletRequest request,HttpServletResponse response, Iterable<?> data
            , ExcelWriter writer,String title)
            throws IOException {
        writer.write(data, true);
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        String sFileName = title + ".xls";
        //兼容各种浏览器文件下载时中文乱码的问题
        if(userAgent.contains("MSIE") || userAgent.contains("TRIDENT") || userAgent.contains("EDGE")){
            sFileName = URLEncoder.encode(sFileName,"UTF-8");
            sFileName = sFileName.replace("+", "%20");
        } else {
            sFileName = new String(sFileName.getBytes(), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", sFileName));
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out);
        writer.close();
    }
}
