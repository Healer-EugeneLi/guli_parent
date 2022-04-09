package com.atguigu.commonutils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName: JwtUtils
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/4/6
 * @Time: 15:52
 */

public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;//token过期时间的设置
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";//指定一个密钥来生成

    /**
     * 生成token字符串的方法
     * @param id
     * @param nickname
     * @return
     */
    public static String getJwtToken(String id, String nickname){

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")//设置头信息
                .setHeaderParam("alg", "HS256")
                .setSubject("guli-user")//分类
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//设置过期时间
                .claim("id", id)//设置token的主体信息 如果有多个的话 可以使用多行
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)//字符串哈希部分
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 通过request 获取token 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        //得到字符串的主体部分
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");//获取id
    }
}


