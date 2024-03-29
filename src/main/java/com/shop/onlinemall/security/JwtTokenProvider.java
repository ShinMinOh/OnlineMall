package com.shop.onlinemall.security;

import static com.shop.onlinemall.common.error.ErrorCode.INVALID_JWT;

import com.shop.onlinemall.security.exception.InvalidJwtException;
import com.shop.onlinemall.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private final String SECRET_KEY;
  private static final String ISSUER = "SHOP";
  private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 6; //6시간
  private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 14; //2주


  public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) { this.SECRET_KEY = secretKey; }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
  }

  public String createAccessToken(User user){
    Date now = new Date();
    return Jwts.builder()
        .setSubject(user.getId().toString())
        .setIssuer(ISSUER)
        .setIssuedAt(now)
        .claim("role", user.getRole().toString())
        .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  public String createRefreshToken(User user) {
    Date now = new Date();
    return Jwts.builder()
        .setSubject(user.getId().toString())
        .setIssuer(ISSUER)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  public void validateToken(String token) {
    try {
      getClaimsFromToken(token);
    } catch (JwtException | IllegalArgumentException e) {
      throw new InvalidJwtException(e.getMessage(), INVALID_JWT);
    }
  }

  public String getSubjectFromToken(String token){
    return getClaimsFromToken(token).getSubject();
  }

  public String getRoleFromToken(String token){
    return getClaimsFromToken(token).get("role").toString();
  }

  //payload
  private Claims getClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
        .parseClaimsJws(token).getBody();
  }
}
