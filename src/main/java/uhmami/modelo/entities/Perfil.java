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
@Table(name = "perfiles")
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * El identificador único del perfil.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL")
    private Integer id;

    /**
     * El nombre del perfil.
     */
    private String nombre;
}
