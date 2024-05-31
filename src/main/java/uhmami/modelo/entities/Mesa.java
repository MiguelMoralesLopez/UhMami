package uhmami.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa una mesa en la base de datos.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 * 
 * Esta clase implementa {@link Serializable} para permitir la serialización de sus instancias.
 */
@Data
@Entity
@Table(name = "mesas")
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private Integer comensales;
=======
    /**
     * El identificador único de la mesa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
>>>>>>> main

    /**
     * El número de comensales que pueden ocupar la mesa.
     */
    @Column(name = "comensales")
    private Integer comensales;
}
