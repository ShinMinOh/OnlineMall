package com.shop.onlinemall.security.filter;

//import com.shop.onlinemall.user.User;
import com.shop.onlinemall.security.JwtTokenProvider;
import com.shop.onlinemall.security.exception.InvalidJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException{

    String jwtToken = getJwtFromRequest(request);

    try {
      jwtTokenProvider.validateToken(jwtToken);

      UserDetails userDetails = getUserDetails(jwtTokenProvider.getSubjectFromToken(jwtToken),
          jwtTokenProvider.getRoleFromToken(jwtToken));
      Authentication authentication = getAuthentication(userDetails);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }catch (InvalidJwtException invalidJwtException){
      request.setAttribute("exception", invalidJwtException.getErrorCode());
    }

    filterChain.doFilter(request, response);
  }

  private Authentication getAuthentication(UserDetails userDetails) {
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  private UserDetails getUserDetails(String userId, String role) {
    return User.builder().username(userId).password("").authorities(role).build();
  }

  //requstHeader에서 token정보 추출
  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
      return bearerToken.substring(7);
    }
    return null;
  }
}
