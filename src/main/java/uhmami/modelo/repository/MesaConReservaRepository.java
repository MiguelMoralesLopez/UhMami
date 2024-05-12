package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Mesa;
import uhmami.modelo.entities.MesaConReserva;
import uhmami.modelo.entities.Reserva;

@Repository
public interface MesaConReservaRepository extends JpaRepository<MesaConReserva, Reserva>{
	
	@Query("SELECT m FROM MesaConReserva m where m.idMesa =?1")
	MesaConReserva findByIdMesa(Integer idMesa);
}
