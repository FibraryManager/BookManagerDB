package com.bookmanagerdb.bookmanagerdb.filter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookmanagerdb.bookmanagerdb.config.auth.UserDetailsImpl;
import com.bookmanagerdb.bookmanagerdb.config.auth.UserDetailsServiceImpl;
import com.bookmanagerdb.bookmanagerdb.utils.TokenUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 对token进行检验
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            request.setAttribute("jwt", authorization.substring(7));
        }

        Object jwt = request.getAttribute("jwt");
        DecodedJWT decodedJWT;
        try {
            decodedJWT = TokenUtil.VerifyJWTToken(jwt.toString());
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        // 数据库设计，userId 作为唯一标识
        Map<String, Claim> claimsMap = decodedJWT.getClaims();
        if (!claimsMap.containsKey("userId")) {
            filterChain.doFilter(request, response);
            return;
        }
        String userId = claimsMap.get("userId").asString();
        request.setAttribute("userId", userId);

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userId);
        if (userDetails != null) {
            request.setAttribute("user", userDetails.getUser());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
