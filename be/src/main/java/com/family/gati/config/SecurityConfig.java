package com.family.gati.config;

import com.family.gati.security.jwt.JwtAuthenticationFilter;
import com.family.gati.security.jwt.JwtTokenProvider;
import com.family.gati.security.jwt.JwtAccessDeniedHandler;
import com.family.gati.security.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtTokenProvider jwtTokenProvider;

    // 패스워드 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // swagger는 security 무시 -> 적용 안되는듯
    @Bean
    public WebSecurityCustomizer configure() {
        return web -> { web.ignoring()
                .antMatchers("/**/join", "/**/login")
                .antMatchers(
                        "/v2/api-docs/**"
                        , "/swagger.json"
                        , "/swagger-ui.html/**"
                        , "/swagger-resources/**"
                        , "/webjars/**"
                );
            };
    }

    // HttpSecurity 설정
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable() // security 기본 로그인 사용 X
                .cors().and() // cors 허용
                .csrf().disable() // csrf 보안 설정 비활성화
                
                .antMatcher("/**").authorizeRequests() // 보호된 리소스 URI에 접근할 수 있는 권한 설정
//                .antMatchers("/**/login", "/**/join").permitAll() // 전체 접근 허용
//                .antMatchers(  "/v2/api-docs/**"
//                        , "/swagger.json"
//                        , "/swagger-ui.html/**"
//                        , "/swagger-resources/**"
//                        , "/webjars/**").permitAll()

                .anyRequest().authenticated() // 다른 경로는 인증필요

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X

                // Jwt filter
                .and()
                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter보다 앞으로 설정
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

        return http.build();
    }

    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://3.34.141.63:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
