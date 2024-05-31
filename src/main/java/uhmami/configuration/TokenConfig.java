package uhmami.configuration;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

/**
 * Clase de configuración para constantes relacionadas con JWT.
 * Contiene la clave secreta, prefijo del token, cabecera de autorización y tipo de contenido.
 */
public class TokenConfig {

    /**
     * Clave secreta utilizada para firmar y verificar los tokens JWT.
     * Se genera usando el algoritmo HS256.
     */
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    /**
     * Prefijo utilizado en el encabezado de autorización para los tokens JWT.
     */
    public static final String PREFIX_TOKEN = "Bearer ";

    /**
     * Nombre del encabezado HTTP donde se espera el token JWT.
     */
    public static final String HEADER_AUTHORIZATION = "Authorization";

    /**
     * Tipo de contenido esperado para las respuestas HTTP.
     */
    public static final String CONTENT_TYPE = "application/json";
}
