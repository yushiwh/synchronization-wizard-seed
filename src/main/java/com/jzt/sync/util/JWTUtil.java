package com.jzt.sync.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jzt.sync.model.ReturnToken;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yushiwh
 * @Description JWT 工具类
 * @Date 2019-04-07
 * @Time 22:48
 */
public class JWTUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    /**
     * 服务端过期时间 24 小时
     */
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;

    /**
     * 客户端过期时间24小时
     */
    private static final long CLIENT_EXPIRE_TIME = 60 * 24 * 60 * 1000;

    /**
     * 服务端密钥
     */
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 客户端密钥
     */
    private static final String CLIENT_SECRET = "RZKJ";


    /**
     * 生成 token, 5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
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
    public static String createClientToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + CLIENT_EXPIRE_TIME);
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


    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            //    logger.info("过期时间：" +  jwt.getClaim("expires").asLong().longValue());

            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 从token中解密用户名和过期时间
     *
     * @param token
     * @return
     */
    public static ReturnToken getToken(String token) {
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


}
