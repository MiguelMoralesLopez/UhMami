package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String>{

}
