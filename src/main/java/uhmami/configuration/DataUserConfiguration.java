package uhmami.configuration;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
=======
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
>>>>>>> main
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import uhmami.modelo.serviceImpl.JpaUserDetailsServiceImpl;

/**
 * Configuración de seguridad para la aplicación.
 * Esta clase define y configura los beans relacionados con la seguridad y las políticas de acceso.
 */
@EnableWebSecurity
@Configuration
public class DataUserConfiguration{
	
<<<<<<< HEAD
	@Bean

	public UserDetailsManager usersCustom(DataSource dataSource) {

	JdbcUserDetailsManager users = 
			new JdbcUserDetailsManager(dataSource); 
	users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?"); 
	users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up " +
	 "inner join usuarios u on u.username = up.username " +
			"inner join perfiles p on p.id_perfil = up.id_perfil " +
			"where u.username = ?");

	return users;

	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable());
		// Los recursos estáticos no requieren autenticación
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/static/**").permitAll()
			.requestMatchers("/img/**").permitAll()
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/").permitAll());
		// El formulario de Login no requiere autenticacion
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
=======
	@Autowired
    private AuthenticationConfiguration authenticationConfiguration;
	
	/**
     * Crea y configura el bean {@link AuthenticationManager}.
     *
     * @return una nueva instancia de {@link AuthenticationManager}
     * @throws Exception si ocurre algún error al obtener el AuthenticationManager
     */
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Crea y configura el bean {@link PasswordEncoder}.
     * Utiliza {@link BCryptPasswordEncoder} para codificar las contraseñas.
     *
     * @return una nueva instancia de {@link PasswordEncoder}
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http el objeto {@link HttpSecurity} a configurar
     * @return la cadena de filtros de seguridad configurada
     * @throws Exception si ocurre algún error al configurar la seguridad HTTP
     */
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
>>>>>>> main
	
	

}
