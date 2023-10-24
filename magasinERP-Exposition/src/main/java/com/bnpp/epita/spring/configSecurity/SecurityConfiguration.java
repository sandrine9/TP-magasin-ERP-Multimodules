package com.bnpp.epita.spring.configSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfiguration {

    /*
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_"); // Remove the ROLE_ prefix
    }

     */


    //bean pour fournit à Spring une instance SecurityFilterChain au démarrage

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/v1/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/produit").permitAll()
                //.antMatchers(HttpMethod.GET,"/api/v1/produit").hasAnyRole("USER", "ADMIN")
                //.antMatchers(HttpMethod.GET,"/api/v1/produit/*").hasAnyRole("USER", "ADMIN")
                //.antMatchers(HttpMethod.POST, "/api/v1/produit").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST, "/api/v1/produit/*").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST,"/api/v1/client").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET,"/api/v1/client/*").hasAnyRole("ADMIN", "USER")
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JWTVerify(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin().disable();
        return http.build();
    }


}
