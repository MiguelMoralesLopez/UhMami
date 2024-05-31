package uhmami.modelo.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar la información de una mesa,
 * incluyendo la fecha y la hora.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 */
@Data
public class MesaDto {
    
    /**
     * La fecha de la reserva de la mesa.
     */
    private String fecha;
    
    /**
     * La hora de la reserva de la mesa.
     */
    private String hora;

}
