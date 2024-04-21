package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer>{

}
