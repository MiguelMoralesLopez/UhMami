package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Mesa;
import uhmami.modelo.entities.MesaConReserva;

@Repository
public interface MesaConReservaRepository extends JpaRepository<MesaConReserva, Mesa>{

}
