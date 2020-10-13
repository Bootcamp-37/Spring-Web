/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcServer.bc37server.securities;


import static bcServer.bc37server.securities.ApplicationUserRole.admin;
import static bcServer.bc37server.securities.ApplicationUserRole.karyawan;
import static bcServer.bc37server.securities.ApplicationUserRole.trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        .authorizeRequests()
        .antMatchers("/api","index","/css/*","/js/*").permitAll()
        .antMatchers("/api/admin/*","/api/admin").hasRole(admin.name())
        .antMatchers("/api/trainer/*").hasRole(trainer.name())
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
    }

    
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails nisaUser= User.builder()
                .username("nisa")
                .password(passwordEncoder.encode("passwordnis4"))
                .roles(karyawan.name())
                .build();
        UserDetails fikriUser= User.builder()
                .username("fikri")
                .password(passwordEncoder.encode("passwordfikr1"))
                .roles(admin.name(),karyawan.name())
                .build();
           UserDetails aqiraUser= User.builder()
                .username("aqira")
                .password(passwordEncoder.encode("passwordaqir4"))
                .roles(admin.name(),karyawan.name(),trainer.name())
                .build();
        return new InMemoryUserDetailsManager(
        nisaUser,fikriUser,aqiraUser
        );
    }
}
