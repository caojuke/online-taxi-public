package com.msb.apipassenger.interceptor;

import org.springframework.cloud.util.ConditionalOnBootstrapEnabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class interceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("interceptor initializing ... ...");
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                //不拦截登录之前的的验证接口
                .excludePathPatterns("/internalTest")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-chek");

    }
}
