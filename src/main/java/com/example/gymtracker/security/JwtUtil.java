//package com.example.gymtracker.security;
//
//import com.example.gymtracker.model.User;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//    @Value("$security.jwt.secret-key")
//    private String jwtSecret;
//    @Value("$security.jwt.expiration-time")
//    private Long jwtExpiration;
//
//    public String getJtwFromHeader(HttpServletRequest request){
//        String bearerToken=request.getHeader("Authorization");
//        if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    public String generateTokenFromUsername(User user){
//        String username=user.getUsername();
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date().getTime()+jwtExpiration)))
//                .signWith()
//                .compact();
//    }
//
//
//}
