package com.gagansp1.myfirstsringboot.config;

import com.gagansp1.myfirstsringboot.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@Profile("dev")
public class SpringSecurity {

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

                    http
                        .csrf(AbstractHttpConfigurer::disable)              // ( csrf is by default enabled ,  and expect a csrf token) Cross-Site Request Forgery (CSRF) is a type of security vulnerability that allows an attacker to perform actions on behalf of a user without their consent. In Java web applications, it is essential to implement measures to protect against CSRF attacks.
                        .authorizeHttpRequests( request->request
                        .requestMatchers( HttpMethod.GET,"/info/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/user/put").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/user/del").authenticated()
                        .requestMatchers("/public/**", "/user/**").permitAll()
                        .requestMatchers("/journal/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());
//               http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}






