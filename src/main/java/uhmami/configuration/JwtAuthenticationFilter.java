package uhmami.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static uhmami.configuration.TokenConfig.CONTENT_TYPE;
import static uhmami.configuration.TokenConfig.HEADER_AUTHORIZATION;
import static uhmami.configuration.TokenConfig.PREFIX_TOKEN;
import static uhmami.configuration.TokenConfig.SECRET_KEY;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uhmami.modelo.entities.Usuario;

/**
 * Filtro de autenticación JWT.
 * Este filtro se encarga de autenticar a los usuarios y generar tokens JWT.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    /**
     * Constructor que inicializa el filtro con el {@link AuthenticationManager}.
     * 
     * @param authenticationManager el gestor de autenticación
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    /**
     * Intenta autenticar a un usuario basado en la información de la solicitud.
     * 
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @return un objeto de autenticación si las credenciales son válidas
     * @throws AuthenticationException si ocurre un error durante la autenticación
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        Usuario usuario = null;
        String username = null;
        String password = null;
        
        try {
            // Intenta leer el objeto Usuario de la solicitud HTTP
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            username = usuario.getUsername();
            password = usuario.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea un token de autenticación con el nombre de usuario y la contraseña
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        
        // Autentica al usuario
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * Ejecutado cuando la autenticación es exitosa.
     * Genera un token JWT y lo añade a la respuesta HTTP.
     * 
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @param chain la cadena de filtros
     * @param authResult el resultado de la autenticación
     * @throws IOException si ocurre un error de E/S
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        // Obtiene los detalles del usuario autenticado
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        
        // Crea los claims del token JWT
        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                .add("username", username)
                .build();
        
        // Genera el token JWT
        String jws = Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // El token dura una hora
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
        
        // Añade el token JWT a la cabecera de la respuesta
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + jws);
        
        // Crea el cuerpo de la respuesta
        Map<String, String> body = new HashMap<>();
        body.put("jws", jws);
        body.put("username", username);
        
        // Escribe el cuerpo de la respuesta en formato JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    /**
     * Ejecutado cuando la autenticación falla.
     * Añade un mensaje de error a la respuesta HTTP.
     * 
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @param failed la excepción de autenticación
     * @throws IOException si ocurre un error de E/S
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
        // Crea el cuerpo de la respuesta de error
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticación. Username o password incorrectos");
        body.put("error", failed.getMessage());
        
        // Escribe el cuerpo de la respuesta de error en formato JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401); // Código de estado HTTP 401: No autorizado
        response.setContentType(CONTENT_TYPE);
    }
}
