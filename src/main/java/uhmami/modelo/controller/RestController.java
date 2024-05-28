package uhmami.modelo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import uhmami.modelo.dto.FechaDto;
import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.serviceImpl.FormServiceImpl;
import uhmami.modelo.serviceImpl.PdfAdminServiceImpl;
import uhmami.modelo.serviceImpl.PdfClienteServiceImpl;
import uhmami.modelo.serviceImpl.ReservaServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private PdfAdminServiceImpl pdfAdminServiceImpl;
	
	@Autowired
	private PdfClienteServiceImpl pdfClienteServiceImpl;
	
	@Autowired
    private FormServiceImpl formServiceImpl;
	
	
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
	
	@PostMapping(value="/generarPdfCliente")
	public String generarPdfCliente(@RequestParam String id) throws IOException {
		String pdf = pdfClienteServiceImpl.generarPdfCliente(id);
		return pdf;
	}
	
	@PostMapping(value="/reservar")
    public String reservar(@RequestBody ReservaDto reservaDto) throws ParseException {
    
        return formServiceImpl.procesarFormReserva(reservaDto);
    }
	
}
