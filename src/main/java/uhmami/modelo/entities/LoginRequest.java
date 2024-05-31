package uhmami.modelo.entities;

import lombok.Data;

/**
 * Clase que representa una solicitud de inicio de sesión.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 */
@Data
public class LoginRequest {
    
    /**
     * El nombre de usuario para el inicio de sesión.
     */
    private String username;
    
    /**
     * La contraseña para el inicio de sesión.
     */
    private String password;
}
