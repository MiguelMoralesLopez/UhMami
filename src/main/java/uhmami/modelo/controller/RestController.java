package uhmami.modelo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uhmami.modelo.dto.FechaDto;
import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.ReservaServiceImpl;
import uhmami.modelo.utils.Utils;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	
	@PostMapping("/reservas/mesasBloqueadas")
	public MesasBloqueadasDto mostrarMesasbloqueadas(@RequestBody MesaDto mesaDto) {

		return utils.mesasReservadas(mesaDto);
	}
	
	@PostMapping(value="/listaReservas")
	public List<Reserva> mostrarReservasPorDia(@RequestBody FechaDto fechaDto) {
		List<Reserva> lista = reservaServiceImpl.buscarPorFecha(fechaDto.getFecha());
		System.out.println("lista" + lista);
		return lista;
	}
	
	
}
