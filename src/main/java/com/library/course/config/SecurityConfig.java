package com.library.course.config;

import com.library.course.controller.CheckUserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

	private final CheckUserInterceptor checkUserInterceptor;

	@Override
	public void addInterceptors( InterceptorRegistry registry){
		registry.addInterceptor(checkUserInterceptor)
				.addPathPatterns("/private/**")
				.excludePathPatterns("/public/**");
	}


}
