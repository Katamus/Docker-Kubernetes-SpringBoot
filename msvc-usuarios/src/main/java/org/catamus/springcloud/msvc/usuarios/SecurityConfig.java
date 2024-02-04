package org.catamus.springcloud.msvc.usuarios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(authz->
                authz
                .requestMatchers("/authorized","/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/", "/{id}").hasAnyAuthority("SCOPE_read","SCOPE_write")
                .requestMatchers(HttpMethod.POST,"/").hasAuthority("SCOPE_write")
                .requestMatchers(HttpMethod.PUT,"/{id}").hasAuthority("SCOPE_write")
                .requestMatchers(HttpMethod.DELETE,"/{id}").hasAuthority("SCOPE_write")
                .anyRequest().authenticated()
        )
        .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2Login(
                oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/msvc-usuarios-client")
        )
        .oauth2Client(withDefaults()).csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
        .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }


}
