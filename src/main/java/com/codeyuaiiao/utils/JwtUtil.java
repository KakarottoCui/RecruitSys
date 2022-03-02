package com.codeyuaiiao.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.Map;
@Component
public class JwtUtil {
    private static final String SING = "123456";

    public static String  getToken(Map<String, String> map){
        Calendar instance = Calendar.getInstance();
        //默认7天
        instance.add(Calendar.DATE,7);

        //创建Jwt builder
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    /**
     * 验证token合法性
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

//    public static DecodedJWT getTokenInfo(){ }

}