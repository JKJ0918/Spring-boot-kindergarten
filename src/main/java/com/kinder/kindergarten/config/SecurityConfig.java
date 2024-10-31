package com.kinder.kindergarten.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                    .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                    .requestMatchers("/**", "/board/**", "/item/**", "/images/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")  //관리자 권한 접속 범위 설정
                    //.requestMatchers("/board/delete/**").hasRole("USER") //유저 권한으로 삭제 가능하게
                    .anyRequest()
                    .authenticated()
            ).formLogin(formLoginCustomizer -> formLoginCustomizer
                    .loginPage("/member/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("email")
                    .failureUrl("/member/login/error")
                    .failureHandler(new CustomAuthenticationFailureHandler())
            ).logout( logoutCustomizer -> logoutCustomizer
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/")

            )
            .build()
            ;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}