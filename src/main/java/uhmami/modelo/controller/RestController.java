package uhmami.modelo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import uhmami.modelo.dto.FechaDto;
import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.PdfDto;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.PdfAdminServiceImpl;
import uhmami.modelo.service.ReservaServiceImpl;
import uhmami.modelo.utils.Utils;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private PdfAdminServiceImpl pdfAdminServiceImpl;
	
	
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
	
	@PostMapping(value="/generarPdfAdmin")
	public PdfDto generarPdfAdmin(@RequestParam String fecha) throws IOException {
		PdfDto pdfDto = pdfAdminServiceImpl.generarPdfAdmin(fecha);
		return pdfDto;
	}
	
	
}
