package uhmami.configuration;

import static uhmami.configuration.TokenConfig.CONTENT_TYPE;
import static uhmami.configuration.TokenConfig.HEADER_AUTHORIZATION;
import static uhmami.configuration.TokenConfig.PREFIX_TOKEN;
import static uhmami.configuration.TokenConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de validación JWT.
 * Este filtro se encarga de validar los tokens JWT en las solicitudes entrantes.
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {

    /**
     * Constructor que inicializa el filtro con el {@link AuthenticationManager}.
     * 
     * @param authenticationManager el gestor de autenticación
     */
    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * Filtra las solicitudes HTTP para validar el token JWT.
     * 
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @param chain la cadena de filtros
     * @throws IOException si ocurre un error de E/S
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Obtiene la cabecera de autorización de la solicitud
        String header = request.getHeader(HEADER_AUTHORIZATION);

        // Si no hay cabecera o no comienza con el prefijo esperado, continua con el siguiente filtro
        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        // Extrae el token de la cabecera
        String token = header.replace(PREFIX_TOKEN, "");

        try {
            // Parsea el token para obtener los claims
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            // Convierte los claims de autoridades a una colección de GrantedAuthority
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class)
            );

            // Crea un token de autenticación con el nombre de usuario y las autoridades
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // Continúa con el siguiente filtro en la cadena
            chain.doFilter(request, response);
        } catch (JwtException e) {
            // Maneja la excepción cuando el token es inválido
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token JWT es invalido!");

            // Escribe la respuesta de error en formato JSON
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // Código de estado HTTP 401: No autorizado
            response.setContentType(CONTENT_TYPE);
        }
    }
}
