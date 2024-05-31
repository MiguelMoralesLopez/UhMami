package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Consulta;

/**
 * Interfaz que define un repositorio para la entidad Consulta.
 * Extiende JpaRepository, lo que proporciona operaciones CRUD estándar
 * y métodos de búsqueda predefinidos.
 */
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

}
