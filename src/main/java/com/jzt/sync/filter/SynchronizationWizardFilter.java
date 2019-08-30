/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: SynchronizationWizardFilter
 * Author:   nick
 * Date:     2019/8/30 9:12
 * Description: 同步精灵过滤器
 * History:
 */
package com.jzt.sync.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jzt.sync.model.ReturnToken;
import com.jzt.sync.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 〈同步精灵过滤器〉
 * 主要是拦截同步精灵过来的请求
 * 去除掉门店通本来的shiro的拦截
 * 定向拦截
 *
 * @author nick
 * @create 2019/8/30
 * @since 1.0.0
 */


@WebFilter(filterName = "SynchronizationWizardFilter", urlPatterns = "/testToken")
public class SynchronizationWizardFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SynchronizationWizardFilter.class);

    /**
     * 客户端密钥
     */
    private static final String CLIENT_SECRET = "RZKJ";

    /**
     * 客户端过期时间24小时
     */
    private static final long CLIENT_EXPIRE_TIME = 60 * 24 * 60 * 1000;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----------------------->SynchronizationWizardFilter过滤器被创建");

    }

    /**
     * 判断是否存在token和验证token的合法性
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //得到 token
        String token = req.getHeader("bear");
        if (Tools.isEmpty(token)) {
            throw new RuntimeException("同步精灵客户端请求的token没有传入");
        }
        ReturnToken rt = getToken(token);
        String createToken = "";
        //计算token，然后进行对比
        if ((rt.getExpires() + "").length() == 10) {
            createToken = createClientToken(rt.getUsername(), new Date(rt.getExpires() * 1000));
        } else {
            createToken = createClientToken(rt.getUsername(), new Date(rt.getExpires()));
        }

        if (!token.equals(createToken)) {
            throw new RuntimeException("同步精灵客户端请求的token不正确");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("----------------------->SynchronizationWizardFilter过滤器被销毁");
    }


    /**
     * 从token中解密用户名和过期时间
     *
     * @param token
     * @return
     */
    public ReturnToken getToken(String token) {
        try {
            ReturnToken rt = new ReturnToken();
            DecodedJWT jwt = JWT.decode(token);
            rt.setExpires(jwt.getClaim("expires").asLong().longValue());
            rt.setUsername(jwt.getClaim("username").asString());
            return rt;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 客户端获取token ，24小时过期
     * 增加了过期时间
     *
     * @param username 登录的用户名
     * @return
     */
    public String createClientToken(String username, Date date) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(CLIENT_SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    //增加过期时间
                    .withClaim("expires", date)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


}