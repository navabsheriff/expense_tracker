package com.fullstack.expense_tracker.security;

import com.fullstack.expense_tracker.entity.User;
import com.fullstack.expense_tracker.repository.UserRepository;
import com.fullstack.expense_tracker.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private  final UserRepository userRepository;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        String email = jwtService.extractEmail(token);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            User user = userRepository.findByEmail(email).orElse(null);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // For now we just print token
        System.out.println("JWT Token: " + token);

        filterChain.doFilter(request, response);
    }
}