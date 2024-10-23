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

        // public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter,
        // AuthenticationProvider authenticationProvider) {
        // this.jwtAuthFilter = jwtAuthFilter;
        // this.authenticationProvider = authenticationProvider;
        // }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(
                                                authorizeRequests -> {
                                                        authorizeRequests

                                                                        .requestMatchers("/api/v1/show_forms",
                                                                                        "/api/v1/show_laboratories",
                                                                                        "/api/v1/show_news",
                                                                                        "/api/v1/fileSystem/{fileName}",
                                                                                        "/api/v1/show_science_committees",
                                                                                        "/api/v1/admin/authenticate")
                                                                        .permitAll()

                                                                        .requestMatchers("/api/v1/**").authenticated()

                                                                        .requestMatchers("api/v1/admin/add_admin",
                                                                                        "/api/v1/admin/change_password",
                                                                                        "/api/v1/admin/update_admin",
                                                                                        "/api/v1/admin/delete_admin",
                                                                                        "/api/v1/admin/show_admins",
                                                                                        "/api/v1/admin/add_form",
                                                                                        "/api/v1/admin/add_laboratory",
                                                                                        "/api/v1/admin/add_news",
                                                                                        "/api/v1/admin/add_science_committee",
                                                                                        "/api/v1/admin/update_form",
                                                                                        "/api/v1/admin/update_laboratory",
                                                                                        "/api/v1/admin/update_news",
                                                                                        "/api/v1/admin/update_science_committee",
                                                                                        "/api/v1/admin/show_forms",
                                                                                        "/api/v1/admin/show_laboratories",
                                                                                        "/api/v1/admin/show_news",
                                                                                        "/api/v1/admin/show_science_committees",
                                                                                        "/api/v1/admin/delete_form",
                                                                                        "/api/v1/admin/delete_laboratory",
                                                                                        "/api/v1/admin/delete_news",
                                                                                        "/api/v1/admin/delete_science_committee")
                                                                        .hasAuthority("SUPER")

                                                                        .requestMatchers("/api/v1/admin/add_form",
                                                                                        "/api/v1/admin/add_laboratory",
                                                                                        "/api/v1/admin/add_news",
                                                                                        "/api/v1/admin/add_science_committee",
                                                                                        "/api/v1/admin/update_form",
                                                                                        "/api/v1/admin/update_laboratory",
                                                                                        "/api/v1/admin/update_news",
                                                                                        "/api/v1/admin/update_science_committee",
                                                                                        "/api/v1/admin/show_forms",
                                                                                        "/api/v1/admin/show_laboratories",
                                                                                        "/api/v1/admin/show_news",
                                                                                        "/api/v1/admin/show_science_committees",
                                                                                        "/api/v1/admin/delete_form",
                                                                                        "/api/v1/admin/delete_laboratory",
                                                                                        "/api/v1/admin/delete_news",
                                                                                        "/api/v1/admin/delete_science_committee")
                                                                        .hasAuthority("ADMIN")

                                                                        .requestMatchers("/**").permitAll();
                                                        http.authenticationProvider(authenticationProvider)
                                                                        .addFilterBefore(jwtAuthFilter,
                                                                                        UsernamePasswordAuthenticationFilter.class);

                                                });
                return http.build();
        }
}
