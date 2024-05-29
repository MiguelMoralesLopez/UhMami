package uhmami.modelo.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar una fecha.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 */
@Data
public class FechaDto {
    
    /**
     * La fecha representada como una cadena.
     */
    private String fecha;

}
