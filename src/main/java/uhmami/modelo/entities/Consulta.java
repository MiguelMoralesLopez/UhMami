package uhmami.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa una consulta en la base de datos.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 * 
 * Esta clase implementa {@link Serializable} para permitir la serialización de sus instancias.
 */
@Data
@Entity
@Table(name = "consultas")
public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private Cliente cliente;
	private String comentario;
	private Date fecha;
=======
    /**
     * El identificador único de la consulta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * El cliente asociado a la consulta.
     */
    @ManyToOne
    @JoinColumn(name = "ID_cliente")
    private Cliente cliente;

    /**
     * El comentario de la consulta.
     */
    @Column(name = "comentario")
    private String comentario;

    /**
     * La fecha de la consulta.
     */
    @Column(name = "fecha")
    private Date fecha;
>>>>>>> main
}
