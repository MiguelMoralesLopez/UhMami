package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Mesa;

/**
 * Interfaz que define un repositorio para la entidad Mesa.
 * Extiende JpaRepository, lo que proporciona operaciones CRUD estándar
 * y métodos de búsqueda predefinidos.
 */
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {

}
