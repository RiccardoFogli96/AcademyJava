package com.library.course.controller;

import com.library.course.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckUserInterceptor implements HandlerInterceptor {


	private final JwtService jwtService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authHeader = request.getHeader("Authorization");
		String token;
		if(authHeader != null){
			token = authHeader.substring(7);
			Claims claims = jwtService.validateToken(token);
			log.info("Token: " + claims.getSubject());

			//Todo: validate token
			return true;
		} else {
		 throw new Exception("Not authorized");
		}


   }

}
