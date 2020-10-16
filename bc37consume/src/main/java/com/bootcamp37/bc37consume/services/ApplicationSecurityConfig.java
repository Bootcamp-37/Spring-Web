/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.services;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin().permitAll();
        http.logout().permitAll();
        http.authorizeRequests()
//                .antMatchers("/**","index","/register","/save").permitAll()
                .antMatchers("/trainer").hasAnyRole(ApplicationUserRole.TRAINER.name())
                .antMatchers("/employee").hasAnyRole(ApplicationUserRole.KARYAWAN.name())
                .antMatchers("/admin").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.csrf().disable();
    }
    
    
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails fikri = User.builder()
                .username("iqwal")
                .password(passwordEncoder.encode("iqwal"))
                .roles(ApplicationUserRole.KARYAWAN.name(),ApplicationUserRole.ADMIN.name(),ApplicationUserRole.TRAINER.name())
                .build();
        
        UserDetails iqwal = User.builder()
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
        return new InMemoryUserDetailsManager(fikri,iqwal,zakky,nisa);
    }
    
    
    
    
}
