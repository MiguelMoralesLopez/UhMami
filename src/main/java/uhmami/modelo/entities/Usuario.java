package uhmami.modelo.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa un usuario en la base de datos.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 * 
 * Esta clase implementa {@link Serializable} para permitir la serialización de sus instancias.
 */
@Data
@Entity
@Table(name = "usuarios")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * El nombre de usuario.
     */
    @Id
    private String username;

    /**
     * Indica si el usuario está habilitado o no.
     */
    private int enabled;

    /**
     * La contraseña del usuario.
     */
    private String password;

    /**
     * Los perfiles asociados al usuario.
     */
    @ManyToMany
    @JoinTable(
        name = "usuario_perfiles",
        joinColumns = { @JoinColumn(name = "USERNAME") },
        inverseJoinColumns = { @JoinColumn(name = "ID_PERFIL") }
    )
    private List<Perfil> perfiles;
}
