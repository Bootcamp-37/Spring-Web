/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author User
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter  {
    
    private final PasswordEncoder passwordEncoder;
   
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    } 
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails iqwal = User.builder()
                .username("iqwal")
                .password(passwordEncoder.encode("iqwal"))
                .roles(ApplicationUserRole.KARYAWAN.name(),ApplicationUserRole.ADMIN.name(),ApplicationUserRole.TRAINER.name())
                .build();
        
        UserDetails fikri = User.builder()
                .username("fikri")
                .password(passwordEncoder.encode("fikri"))
                .roles(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.KARYAWAN.name())
                .build();
        UserDetails zakky = User.builder()
                .username("zakky")
                .password(passwordEncoder.encode("zakky"))
                .roles(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.TRAINER.name())
                .build();
        UserDetails nisa = User.builder()
                .username("nisa")
                .password(passwordEncoder.encode("nisa"))
                .roles(ApplicationUserRole.TRAINER.name())
                .build();
        return new InMemoryUserDetailsManager(iqwal,fikri,zakky,nisa);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.logout().permitAll();
        http.authorizeRequests()
                .antMatchers("/css/*","/js/*").permitAll()
                .antMatchers("/register","/save").permitAll()
                .antMatchers("/user/trainer").hasAnyRole(ApplicationUserRole.TRAINER.name())
                .antMatchers("/user/employee").hasAnyRole(ApplicationUserRole.KARYAWAN.name())
                .antMatchers("/user/admin").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
        http.csrf().disable();
    }
}
