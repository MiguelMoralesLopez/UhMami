package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
