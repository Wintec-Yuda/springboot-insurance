package com.livecode.insurance.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.POST,"/api/auth/login", "/api/auth/register").permitAll()

                .requestMatchers(HttpMethod.GET,"/api/users", "/api/insurances", "/api/treatment-insurances").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/treatment-insurances/insurance/*", "/api/insurances/*", "/api/users/*").hasRole("USER")
                .requestMatchers(HttpMethod.GET,"/api/treatments").hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.POST,"/api/treatment-insurances", "/api/insurances").hasRole("USER")
                .requestMatchers(HttpMethod.POST,"/api/treatments").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/users/logout").hasAnyRole("USER", "ADMIN")


                .requestMatchers(HttpMethod.PUT,"/api/insurances/expiry-date/*", "/api/treatments/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/users/*").hasRole("USER")

                .requestMatchers(HttpMethod.DELETE,"/api/users/*", "/api/insurances/*", "/api/treatment-insurances/*", "/api/treatments/*").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = Dotenv.load();
        String databaseUrl = dotenv.get("DATABASE_URL");
        String databaseUsername = dotenv.get("DATABASE_USERNAME");
        String databasePassword = dotenv.get("DATABASE_PASSWORD");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }
}
