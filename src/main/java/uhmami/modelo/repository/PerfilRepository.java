package uhmami.modelo.repository;

<<<<<<< HEAD
public interface PerfilRepository {
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Perfil;

/**
 * Interfaz que define un repositorio para la entidad Perfil.
 * Extiende JpaRepository, lo que proporciona operaciones CRUD estándar
 * y métodos de búsqueda predefinidos.
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
>>>>>>> main

}
