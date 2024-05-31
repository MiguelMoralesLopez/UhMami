package uhmami.modelo.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar la información necesaria para modificar una reserva.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 */
@Data
public class ModificarReservasDto {

    /**
     * El identificador de la reserva.
     */
    private String idReserva;
    
    /**
     * El número de comensales para la reserva.
     */
    private Integer comensales;
    
    /**
     * Las observaciones adicionales para la reserva.
     */
    private String observaciones;
    
    /**
     * La fecha de la reserva.
     */
    private String fecha;
    
    /**
     * La hora de la reserva.
     */
    private String hora;
    
    /**
     * El identificador del cliente que realizó la reserva.
     */
    private Integer idCliente;
    
    /**
     * El nombre del cliente que realizó la reserva.
     */
    private String nombre;
    
    /**
     * Los apellidos del cliente que realizó la reserva.
     */
    private String apellidos;
    
    /**
     * El correo electrónico del cliente que realizó la reserva.
     */
    private String email;
    
    /**
     * El número de teléfono del cliente que realizó la reserva.
     */
    private Integer telefono;
}
