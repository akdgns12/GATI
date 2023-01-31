package com.family.gati.config;

import com.family.gati.config.auth.JwtAuthenticationFilter;
import com.family.gati.exception.CustomAccessDeniedHandler;
import com.family.gati.exception.CustomAuthenticationEntryPoint;
import com.family.gati.exception.FormAuthenticationFailureHandler;
import com.family.gati.exception.FormAuthenticationSuccessHandler;
import com.family.gati.service.auth.CustomUserDetailService;
import com.family.gati.service.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FormAuthenticationSuccessHandler formAuthenticationSuccessHandler;
    private final FormAuthenticationFailureHandler formAuthenticationFailureHandler;
    private final CustomUserDetailService userDetailsService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtTokenProvider jwtTokenProvider;

    // 패스워드 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 정적 Resource는 Security에서 제외
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/favicon.ico", "/resources/**", "/error");
    }

    // Custom userDetailsService 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // HttpSecurity 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api이므로 기본설정 미사용
                .csrf().disable() // rest api이므로 csrf 보안 미사용
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt로 인증하므로 세션 미사용
                .and()
                .oauth2Login()
                // 프론트엔드에서 백엔드로 소셜로그인 요청을 보내는 URI
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                // 인증 과정이 끝나면 인증 code와 함께 리다이렉트할 URI
                .redirectionEndpoint()
                .baseUri("login/oauth/code/*")
                .and()
                // Provider로부터 획득한 유저정보를 다룰 service class를 지정합니다
                .userInfoEndpoint()
                .userService()
                .authorizeRequests()
                .antMatchers("/sign/**").permitAll()
                .antMatchers("/social/**").permitAll()
                .antMatchers("/exception/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 인증이 되지 않은채로 인증이 필요한 페이지에 접근했을 경우 Handling
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt
    }

    // 인증과정에서 session이 아닌 cookie 기반 인가로

    private AuthorizationRequestRepository<OAuth2AuthorizationRequest> oAuth2AuthorizationRequestBasedOnCookieRepository() {

    }
}
