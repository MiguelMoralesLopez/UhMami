package uhmami.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para representar la información de una reserva.
 * Utiliza las anotaciones de Lombok {@link Data}, {@link AllArgsConstructor} y {@link NoArgsConstructor}
 * para generar automáticamente los métodos getter, setter, toString, equals, hashCode, 
 * un constructor con todos los argumentos y un constructor sin argumentos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {

    /**
     * El nombre del cliente que realiza la reserva.
     */
    private String nombre;

    /**
     * Los apellidos del cliente que realiza la reserva.
     */
    private String apellidos;

    /**
     * El correo electrónico del cliente que realiza la reserva.
     */
    private String email;

    /**
     * El número de teléfono del cliente que realiza la reserva.
     */
    private String telefono;

    /**
     * El número de comensales para la reserva.
     */
    private String comensales;

    /**
     * La fecha de la reserva.
     */
    private String fecha;

    /**
     * La hora de la reserva.
     */
    private String hora;

    /**
     * La mesa asignada para la reserva.
     */
    private String mesa;

    /**
     * Las observaciones adicionales para la reserva.
     */
    private String observaciones;
}
