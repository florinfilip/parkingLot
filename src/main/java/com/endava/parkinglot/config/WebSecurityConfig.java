package com.endava.parkinglot.config;

import com.endava.parkinglot.service.Impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


@Bean
public UserDetailsService userDetailsService(){
    return new UserServiceImpl();
}

@Bean
public BCryptPasswordEncoder passwordEncoder(){

    return new BCryptPasswordEncoder();
}

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                .mvcMatchers("/users/**").hasAuthority("ADMIN")
                .mvcMatchers("/user/**").hasAuthority("ADMIN")
                .mvcMatchers("/addUser/**").hasAuthority("ADMIN")
                .mvcMatchers("/deleteUser").hasAuthority("ADMIN")
                .mvcMatchers("/parkingSpots/**").hasAuthority("ADMIN")
                .mvcMatchers("/parkingSpots/**").hasAuthority("ADMIN")
                .anyRequest().authenticated();



//
//
//        http.httpBasic();
//        http.csrf().disable().anonymous().principal("Guest").authorities("GUEST")
//                .and()
//                .authorizeRequests()
//                .mvcMatchers("/users").hasRole("ADMIN")
//                .mvcMatchers("/user").hasAuthority("ADMIN")
//                .mvcMatchers("/addUser").hasAuthority("ADMIN")
//                .mvcMatchers("/deleteUser").hasAuthority("ADMIN")
//                .anyRequest().permitAll();

    }



}
