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
 * Entidad que representa un perfil en la base de datos.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 * 
 * Esta clase implementa {@link Serializable} para permitir la serialización de sus instancias.
 */
@Data
@Entity
<<<<<<< HEAD
@Table(name="consultas")
=======
@Table(name = "perfiles")
>>>>>>> main
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private String nombre;
=======
    /**
     * El identificador único del perfil.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL")
    private Integer id;
>>>>>>> main

    /**
     * El nombre del perfil.
     */
    private String nombre;
}
