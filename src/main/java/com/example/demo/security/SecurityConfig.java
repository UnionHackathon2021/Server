package com.example.demo.security;

import com.example.demo.exception_advice.handler.CustomAccessDeniedHandler;
import com.example.demo.exception_advice.handler.CustomAuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http
                .csrf().disable() // rest api이므로 기본설정 사용안함, 기본 설정은 비인증시 로그인 폼으로 리다이렉트
                .httpBasic().disable(); //rest api, csrf보안이 필요없다.

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers("/exception/**").permitAll()
//                .antMatchers("/v1/test").authenticated()

                // Disallow everything else..
                .anyRequest().authenticated()

                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
        http
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/public")

                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
                .antMatchers("/exception/**")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

}
