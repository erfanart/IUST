package com.example.demo.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(
                        authorizeRequests ->{
                            authorizeRequests



                                    .requestMatchers("api/v1/admin/authenticate").permitAll()
                                    .requestMatchers("/api/v1/super_admin/authenticate").permitAll()

                                    .requestMatchers("api/v1/home/show_forms").permitAll()
                                    .requestMatchers("api/v1/home/show_laboratories").permitAll()
                                    .requestMatchers("api/v1/home/show_news").permitAll()
                                    .requestMatchers("api/v1/home/show_science_committees").permitAll()
                                    .requestMatchers("api/v1/admin//fileSystem/{fileName}").permitAll()
                                    
                                    
                                    .requestMatchers("/api/v1/super_admin/change_password").hasAuthority("SUPER")
                                    .requestMatchers("/api/v1/super_admin/add_admin").hasAuthority("SUPER")
                                    .requestMatchers("/api/v1/super_admin/delete_admin").hasAuthority("SUPER")
                                    .requestMatchers("/api/v1/super_admin/show_admins").hasAuthority("SUPER")
                                    .requestMatchers("/api/v1/super_admin/update_admin").hasAuthority("SUPER")



                                    
                                    .requestMatchers("api/v1/admin/change_password").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/add_form").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/add_laboratory").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/add_news").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/add_science_committee").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/update_form").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/update_laboratory").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/update_news").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/update_science_committee").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/show_forms").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/show_laboratories").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/show_news").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/show_science_committees").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/delete_form").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/delete_laboratory").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/delete_news").hasAuthority("ADMIN")
                                    .requestMatchers("api/v1/admin/delete_science_committee").hasAuthority("ADMIN");


                            http.authenticationProvider(authenticationProvider)
                                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                        });
        return http.build();
    }
}
