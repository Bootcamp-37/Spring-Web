/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.securities;


import static com.bootcamp37.bc37consume.securities.ApplicationUserPermission.karyawan_write;
import static com.bootcamp37.bc37consume.securities.ApplicationUserRole.admin;
import static com.bootcamp37.bc37consume.securities.ApplicationUserRole.karyawan;
import static com.bootcamp37.bc37consume.securities.ApplicationUserRole.trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author Laila
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final PasswordEncoder passwordEncoder;
    @Autowired
    
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
http
//        (:"/","index","/css/*","/js/*")
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/css/*","/js/*").permitAll()
        .antMatchers("/api/admin/*","/api/admin").hasRole(admin.name())
        .antMatchers("/api/trainer/*").hasRole(trainer.name())
//        .antMatchers(HttpMethod.POST, "/save").hasAuthority(karyawan_write.name())
//        .antMatchers("/save").hasAnyRole(admin.name(), trainer.name(), karyawan.name())
        .anyRequest()
        .authenticated()
//        .and()
//        .httpBasic();
        .and()
        .formLogin()
        .loginPage("/login").permitAll();
    }

    
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails nisaUser= User.builder()
                .username("nisa")
                .password(passwordEncoder.encode("pnis4"))
                .roles(karyawan.name())
                .build();
        UserDetails fikriUser= User.builder()
                .username("fikri")
                .password(passwordEncoder.encode("pfikr1"))
                .roles(admin.name(),karyawan.name())
                .build();
           UserDetails aqiraUser= User.builder()
                .username("aqira")
                .password(passwordEncoder.encode("paqir4"))
                .roles(admin.name(),karyawan.name(),trainer.name())
                .build();
        return new InMemoryUserDetailsManager(
        nisaUser,fikriUser,aqiraUser
        );
    }
}
