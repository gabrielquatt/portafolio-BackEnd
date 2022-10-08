package com.example.api.security;

import com.example.api.security.jwt.JwtEntryPoint;
import com.example.api.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity {
    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()

                // --- No Authenticated --- //
                .antMatchers(HttpMethod.GET,"/api/cloudinary/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/domicile/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/education/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/experience/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/language/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/person/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/proyect/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/skill/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/img/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                // --- No Authenticated --- //

        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
