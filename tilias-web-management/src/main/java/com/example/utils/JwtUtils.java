package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * JWT令牌工具类
 * 包含：生成令牌、解析令牌两个核心方法
 */
public class JwtUtils {

    // 安全的256位密钥（base64编码，需用你实际生成的密钥替换此处）
    private static final String SECRET_KEY = "u8Qw1k2v3x4y5z6A7B8C9D0E1F2G3H4I5J6K7L8M9N0P1Q2R3S4T5U6V7W8X9Y0Z";
    // 过期时间：12小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷信息（如用户id、username等）
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 指定加密算法HS256 + 密钥（用Base64解码后的字节数组）
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(SECRET_KEY))
                // 添加自定义载荷信息
                .addClaims(claims)
                // 设置过期时间：当前时间 + 12小时
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 生成紧凑的JWT字符串
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param token 待解析的JWT令牌字符串
     * @return 解析后的载荷信息（Claims对象）
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                // 指定解密密钥（与生成密钥一致，Base64解码）
                .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                // 解析令牌，验证签名和过期时间
                .parseClaimsJws(token)
                // 获取载荷主体
                .getBody();
    }
}