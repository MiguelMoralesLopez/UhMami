package uhmami.modelo.repository;

<<<<<<< HEAD
public interface UsuarioRepository {
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Usuario;

/**
 * Interfaz que define un repositorio para la entidad Usuario.
 * Extiende JpaRepository, lo que proporciona operaciones CRUD estándar
 * y métodos de búsqueda predefinidos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
>>>>>>> main

}
