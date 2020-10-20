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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
//    private DataSource dataSource;
//    
//    @Autowired
//    public ApplicationSecurityConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//     
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());
////                .usersByUsernameQuery(
////                "SELECT username,password from users where username = ?")
////                .authoritiesByUsernameQuery(
////                "SELECT u.username, r.role FROM userroles ur JOIN user u ON ur.user_id = u.id JOIN role r ON ur.role_id = r.id WHERE u.username = ?");
//    }
    
    @Override
    public void configure (WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.logout().permitAll();
        http.authorizeRequests()
//                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/test/trainer").hasAnyRole(ApplicationUserRole.TRAINER.name())
                .antMatchers("/test/employee").hasAnyRole(ApplicationUserRole.KARYAWAN.name())
                .antMatchers("/test/admin").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin() 
                .loginPage("/login")
                .successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
                    for (GrantedAuthority authority : auth.getAuthorities()) {
                       System.out.println(authority.getAuthority());
                    }
                    System.out.println(auth.getName());
                    res.sendRedirect("/"); // Redirect user to index/home page
                 })
                 .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
                    String errMsg="";
                    if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
                       errMsg="Invalid username or password.";
                    }else{
                       errMsg="Unknown error - "+exp.getMessage();
                    }
                    req.getSession().setAttribute("message", errMsg);
                    res.sendRedirect("/login"); // Redirect user to login page with error message.
                 })
                .permitAll()
                .and()
                .csrf().disable();
    }
    
    
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails fikri = User.builder()
                .username("fikri")
                .password(passwordEncoder.encode("1234"))
                .roles(ApplicationUserRole.KARYAWAN.name(),ApplicationUserRole.ADMIN.name(),ApplicationUserRole.TRAINER.name())
                .build();
        
        UserDetails iqwal = User.builder()
                .username("iqwal")
                .password(passwordEncoder.encode("2233"))
                .roles(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.KARYAWAN.name())
                .build();
        UserDetails zakky = User.builder()
                .username("zakky")
                .password(passwordEncoder.encode("5678"))
                .roles(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.TRAINER.name())
                .build();
        UserDetails nisa = User.builder()
                .username("nisa")
                .password(passwordEncoder.encode("9876"))
                .roles(ApplicationUserRole.TRAINER.name())
                .build();
        return new InMemoryUserDetailsManager(fikri,iqwal,zakky,nisa);
    }
    
    
    
    
}
