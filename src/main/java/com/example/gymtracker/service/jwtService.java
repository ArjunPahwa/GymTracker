//package com.example.gymtracker.service;
//
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class jwtService {
//
//    @Value("$security.jwt.secret-key")
//    private String secretKey;
//    @Value("$security.jwt.expiration-time")
//    private Long jwtExpiration;
//
//    private String extractUsername(String token){
//        return extractClaim(token, Claims::getSubject)
//    }
//
//}
