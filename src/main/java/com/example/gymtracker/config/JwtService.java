package com.example.gymtracker.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    //extract all claims
    //extract one claim
    //extract secret key
    //build token
    //istokenvalid
    //extract token expiry claim
    //is token expired?


    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Value("${security.jwt.expiration-time}")
    private long expiration_time;



    public String extractUsername(String jwtToken){

        return extractClaim(jwtToken,Claims::getSubject);
    }

    public Claims extractAllClaims(String jwtToken){

        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .signWith(getSigningKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration_time))
                .compact();


    }

    public boolean isTokenValid(String jwtToken,UserDetails userDetails){

            return extractUsername(jwtToken).equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);

    }

    public boolean isTokenExpired(String jwtToken){
        return extractExpiry(jwtToken).before(new Date());
    }
    public Date extractExpiry(String jwtToken){
        return extractClaim(jwtToken,Claims::getExpiration);
    }


    public <T> T extractClaim(String jwtToken, Function<Claims,T> claimResolver){
        Claims claims=extractAllClaims(jwtToken);
        return claimResolver.apply(claims);

    }

    private Key getSigningKey() {
        byte[] key  = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

}
