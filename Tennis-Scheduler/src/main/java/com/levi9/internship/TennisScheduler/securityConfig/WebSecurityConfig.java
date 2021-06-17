package com.levi9.internship.TennisScheduler.securityConfig;

import com.levi9.internship.TennisScheduler.security.TokenUtils;
import com.levi9.internship.TennisScheduler.security.auth.RestAuthenticationEntryPoint;
import com.levi9.internship.TennisScheduler.security.auth.TokenAuthenticationFilter;
import com.levi9.internship.TennisScheduler.serviceImpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final TokenUtils tokenUtils;
    private final CustomUserDetailsService jwtUserDetailsService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public WebSecurityConfig(TokenUtils tokenUtils, CustomUserDetailsService jwtUserDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.tokenUtils = tokenUtils;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
              .and().authorizeRequests().antMatchers("/auth/giveMeAccountBack","/auth/signup","/auth/login", "/auth/**").permitAll()
              .and().authorizeRequests().antMatchers(HttpMethod.GET, "/tennis/tennis-player").permitAll().anyRequest().authenticated()
              .and().addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }


}
