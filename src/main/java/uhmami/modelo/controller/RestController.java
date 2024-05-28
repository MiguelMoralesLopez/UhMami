package uhmami.modelo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import uhmami.modelo.dto.FechaDto;
import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.PdfDto;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.serviceImpl.MesaServiceImpl;
import uhmami.modelo.serviceImpl.PdfAdminServiceImpl;
import uhmami.modelo.serviceImpl.ReservaServiceImpl;
import uhmami.modelo.utils.Utils;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private PdfAdminServiceImpl pdfAdminServiceImpl;
	
	
	@PostMapping("/reservas/mesasBloqueadas")
	public MesasBloqueadasDto mostrarMesasbloqueadas(@RequestBody MesaDto mesaDto) {

		return reservaServiceImpl.mesasReservadas(mesaDto);
	}
	
	@PostMapping(value="/listaReservas")
	public List<Reserva> mostrarReservasPorDia(@RequestBody FechaDto fechaDto) {
		List<Reserva> lista = reservaServiceImpl.buscarPorFecha(fechaDto.getFecha());
		System.out.println("lista" + lista);
		return lista;
	}
	
	@PostMapping(value="/generarPdfAdmin")
	public String generarPdfAdmin(@RequestParam String fecha) throws IOException {
		String pdf = pdfAdminServiceImpl.generarPdfAdmin(fecha);
		return pdf;
	}
	
	
	
	
}
