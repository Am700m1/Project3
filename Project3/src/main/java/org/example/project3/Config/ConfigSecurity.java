package org.example.project3.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/register/employee").permitAll()
                        .requestMatchers("/api/v1/account/create", "/api/v1/account/details/{accountId}", "/api/v1/account/my-accounts", "/api/v1/account/deposit/{accountId}/{amount}", "/api/v1/account/withdraw/{accountId}/{amount}", "/api/v1/account/transfer/{fromAccountId}/{toAccountId}/{amount}").hasAuthority("CUSTOMER")
                        .requestMatchers("/api/v1/account/get-all", "/api/v1/account/update/{accountId}", "/api/v1/account/delete/{accountId}", "/api/v1/account/active/{accountId}", "/api/v1/account/block/{accountId}").hasAnyAuthority("EMPLOYEE", "ADMIN")
                        .anyRequest().authenticated()
                )

                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )

                .httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
