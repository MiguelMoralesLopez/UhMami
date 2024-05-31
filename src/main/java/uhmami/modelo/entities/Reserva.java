package uhmami.modelo.entities;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.time.LocalDate;

>>>>>>> main
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
>>>>>>> main
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa una reserva en la base de datos.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 * 
 * Esta clase implementa {@link Serializable} para permitir la serialización de sus instancias.
 */
@Data
@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private Integer comensales;
	private String observaciones;
=======
    /**
     * El identificador único de la reserva.
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * El número de comensales para la reserva.
     */
    private Integer comensales;

    /**
     * Las observaciones adicionales para la reserva.
     */
    private String observaciones;

    /**
     * El cliente asociado a la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "ID_cliente")
    private Cliente cliente;

    /**
     * La mesa asignada para la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "mesa")
    private Mesa mesa;

    /**
     * La fecha de la reserva.
     */
    @Column(name = "fecha")
    private LocalDate fecha;

    /**
     * La hora de la reserva.
     */
    @Column(name = "hora")
    private String hora;
>>>>>>> main

}
