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
    
    /**
     * Maneja las solicitudes POST para la ruta "/reservas/mesasBloqueadas".
     * 
     * @param mesaDto el objeto {@link MesaDto} que contiene la información de la mesa.
     * @return un objeto {@link MesasBloqueadasDto} que representa las mesas bloqueadas.
     */
    @PostMapping("/reservas/mesasBloqueadas")
    public MesasBloqueadasDto mostrarMesasbloqueadas(@RequestBody MesaDto mesaDto) {
        return reservaServiceImpl.mesasReservadas(mesaDto);
    }
    
    /**
     * Maneja las solicitudes POST para la ruta "/listaReservas".
     * 
     * @param fechaDto el objeto {@link FechaDto} que contiene la fecha para la búsqueda de reservas.
     * @return una lista de objetos {@link Reserva} que representan las reservas del día especificado.
     */
    @PostMapping(value = "/listaReservas")
    public List<Reserva> mostrarReservasPorDia(@RequestBody FechaDto fechaDto) {
        List<Reserva> lista = reservaServiceImpl.buscarPorFecha(fechaDto.getFecha());
        System.out.println("lista" + lista);
        return lista;
    }
    
    /**
     * Maneja las solicitudes POST para la ruta "/generarPdfAdmin".
     * 
     * @param fecha la fecha para la cual se generará el PDF.
     * @return una cadena que representa el PDF generado.
     * @throws IOException si ocurre un error durante la generación del PDF.
     */
    @PostMapping(value = "/generarPdfAdmin")
    public String generarPdfAdmin(@RequestParam String fecha) throws IOException {
        String pdf = pdfAdminServiceImpl.generarPdfAdmin(fecha);
        return pdf;
    }
    
    /**
     * Maneja las solicitudes POST para la ruta "/generarPdfCliente".
     * 
     * @param id el ID para el cual se generará el PDF.
     * @return una cadena que representa el PDF generado.
     * @throws IOException si ocurre un error durante la generación del PDF.
     */
    @PostMapping(value = "/generarPdfCliente")
    public String generarPdfCliente(@RequestParam String id) throws IOException {
        String pdf = pdfClienteServiceImpl.generarPdfCliente(id);
        return pdf;
    }
    
    /**
     * Maneja las solicitudes POST para la ruta "/reservar".
     * 
     * @param reservaDto el objeto {@link ReservaDto} que contiene la información de la reserva.
     * @return una cadena que representa el resultado del procesamiento de la reserva.
     * @throws ParseException si ocurre un error durante el análisis de la fecha.
     */
    @PostMapping(value = "/reservar")
    public String reservar(@RequestBody ReservaDto reservaDto) throws ParseException {
        return formServiceImpl.procesarFormReserva(reservaDto);
    }
}
