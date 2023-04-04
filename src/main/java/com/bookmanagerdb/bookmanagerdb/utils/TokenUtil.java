package com.bookmanagerdb.bookmanagerdb.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class TokenUtil
{
    //Issuer
    public static final String ISSUER = "Test.com";
    //Audience
    public static final String AUDIENCE = "Client";
    //密钥
    public static final String KEY = "ThisIsMySecretKey";
    //算法
    public static final Algorithm ALGORITHM = Algorithm.HMAC256(TokenUtil.KEY);
    //Header
    public static final Map<String, Object> HEADER_MAP = new HashMap<>()
    {
        {
            put("alg", "HS256");
            put("typ", "JWT");
        }
    };

    /**
     * 生成 Token 字符串
     *
     * @param claimMap claim 数据
     * @return Token 字符串
     */
    public static String GenerateToken(Map<String, String> claimMap)
    {
        Date nowDate = new Date();
        //120 分钟过期
        Date expireDate = TokenUtil.AddDate(nowDate, 2 * 60);

        //Token 建造器
        JWTCreator.Builder tokenBuilder = JWT.create();

        for (Map.Entry<String, String> entry : claimMap.entrySet())
        {
            //Payload 部分，根据需求添加
            tokenBuilder.withClaim(entry.getKey(), entry.getValue());
        }

        //token 字符串
        String token = tokenBuilder.withHeader(TokenUtil.HEADER_MAP)//Header 部分
                .withIssuer(TokenUtil.ISSUER)//issuer
                .withAudience(TokenUtil.AUDIENCE)//audience
                .withIssuedAt(nowDate)//生效时间
                .withExpiresAt(expireDate)//过期时间
                .sign(TokenUtil.ALGORITHM);//签名，算法加密

        return token;
    }

    /**
     * 时间加法
     *
     * @param date   当前时间
     * @param minute 持续时间（分钟）
     * @return 时间加法结果
     */
    private static Date AddDate(Date date, Integer minute)
    {
        if (null == date)
        {
            date = new Date();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime();
    }

    /**
     * 验证 Token
     *
     * @param webToken 前端传递的 Token 字符串
     * @return Token 字符串是否正确
     * @throws Exception 异常信息
     */
    public static DecodedJWT VerifyJWTToken(String webToken) throws Exception
    {
        if (webToken.isBlank()) throw new IllegalAccessException("Token should not be empty");
        //JWT验证器
        JWTVerifier verifier = JWT.require(TokenUtil.ALGORITHM).withIssuer(TokenUtil.ISSUER).build();
        //解码
        DecodedJWT jwt = verifier.verify(webToken);//如果 token 过期，此处就会抛出异常
        return jwt;
    }



}
