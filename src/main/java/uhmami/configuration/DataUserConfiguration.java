package uhmami.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import uhmami.modelo.service.JpaUserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration{
	
	@Autowired
    private AuthenticationConfiguration authenticationConfiguration;
	
	
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests( (authz) -> authz
        .requestMatchers("/static/**").permitAll()
        .requestMatchers("/assets/**").permitAll()
    	.requestMatchers("/img/**").permitAll()
    	.requestMatchers("/styles/**").permitAll()
    	.requestMatchers("/").permitAll()
        .requestMatchers(HttpMethod.POST, "/login").permitAll()
        //Añado requisitos de seguridad para los usuarios
        .requestMatchers(HttpMethod.GET,"/admin").hasRole("ADMINISTRADOR")
        .requestMatchers(HttpMethod.POST,"/listaReservas").hasRole("ADMINISTRADOR")
        .requestMatchers(HttpMethod.POST,"/pdfReservas").hasRole("ADMINISTRADOR")
        .anyRequest().permitAll())
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .addFilter(new JwtValidationFilter(authenticationManager()))
        .csrf(config -> config.disable())
        .formLogin(form -> form.loginPage("/login").permitAll())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
	
	

}
