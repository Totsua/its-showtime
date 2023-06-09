package com.company.showtime.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    /**
     * This page sets up the security of the web application.
     * It determines what user can enter what page, at this time
     * all application users logged in or not can access any page
     * on the application.
     * It typically takes in User Roles to prevent role users accessing
     * Admin level stuff.
     * There's currently no Admins/Role users.
     */
    //baeldung.com/java-config-spring-security

    @Autowired // Details the user inputted being checked - See "CustomUserDetailsService"
    private UserDetailsService userDetailsService;

    @Bean // Password Encoder
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method configures the page securities.
     * It also does the login/logout functionality
     * @param http- the HttpSecurity Object used for the configuration
     * @return the SecurityFilterChain Object
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        // Allowing all users to go to the homepage and register
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .anyRequest().permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/login") // Setting the login page
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                //.defaultSuccessUrl("/redirect", true) // Trying to send the user back to the page before login
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    /**
     * This method verifies the user login - looks at the username and password via userDetailsService
     * (CustomUserDetailsService), the password is decoded and encoded via the passwordEncoder.
     * @param auth - the AuthenticationManagerBuilder Object
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}