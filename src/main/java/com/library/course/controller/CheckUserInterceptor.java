package com.library.course.controller;

import com.library.course.repository.CustomerRepository;
import com.library.course.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckUserInterceptor implements HandlerInterceptor {

	private final CustomerRepository customerRepository;
	private final JwtService jwtService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SecurityException {
		String authHeader = request.getHeader("Authorization");
		String token;
		if(authHeader != null){
			token = authHeader.substring(7);
			Claims claims = jwtService.validateToken(token);
			Date expiration = claims.getExpiration();
			if(customerRepository.findByEmail(claims.getSubject()) != null && expiration.after(new Date())){
				request.setAttribute("email", claims.getSubject());
				return true;
			}
		}
		throw new SecurityException("Not authorized");
   }
}
