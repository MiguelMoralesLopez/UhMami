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
 * Entidad que representa un cliente en la base de datos.
 * Utiliza las anotaciones de Lombok {@link Data} y {@link AllArgsConstructor}
 * para generar automáticamente los métodos getter, setter, toString, equals, hashCode,
 * y un constructor con todos los argumentos.
 * 
 * Esta clase implementa {@link Serializable} para permitir la serialización de sus instancias.
 */
@Data
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private String nombre;
	private String apellidos;
	private String email;
	private Integer telefono;
=======
    /**
     * El identificador único del cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * El nombre del cliente.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Los apellidos del cliente.
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * El correo electrónico del cliente.
     */
    @Column(name = "email")
    private String email;

    /**
     * El número de teléfono del cliente.
     */
    @Column(name = "telefono")
    private Integer telefono;
>>>>>>> main
}
