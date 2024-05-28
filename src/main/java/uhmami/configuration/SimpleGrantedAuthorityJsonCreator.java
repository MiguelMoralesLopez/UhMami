package uhmami.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase abstracta para crear instancias de {@link SimpleGrantedAuthority} a partir de JSON.
 * Utiliza las anotaciones de Jackson para mapear propiedades JSON a campos de la clase.
 */
public abstract class SimpleGrantedAuthorityJsonCreator {

    /**
     * Constructor para crear una instancia de {@link SimpleGrantedAuthority} a partir de JSON.
     * 
     * @param role el nombre de la autoridad (rol) del usuario
     */
    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {
        // Constructor vacío para que Jackson pueda crear instancias de la clase
    }
}
