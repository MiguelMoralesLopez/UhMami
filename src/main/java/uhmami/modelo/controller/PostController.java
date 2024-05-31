package uhmami.modelo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.serviceImpl.FormServiceImpl;
import uhmami.modelo.serviceImpl.ReservaServiceImpl;

/**
 * Controlador para manejar las peticiones POST de la aplicación.
 */
@Controller
public class PostController {
    
    @Autowired
    private FormServiceImpl formServiceImpl;
    
    @Autowired
    private ReservaServiceImpl reservaServiceImpl;
    
    /**
     * Procesa el formulario de contacto.
     *
     * @param nombre el nombre del remitente
     * @param telefono el número de teléfono del remitente
     * @param comentario el comentario del remitente
     * @param redirectAttributes atributos para redireccionamiento
     * @return la redirección a la página de contacto
     */
    @PostMapping(value="/contacto/alta", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String procesarFormContacto(@RequestParam(name="nombre")String nombre,
            @RequestParam(name="telefono")Integer telefono,
            @RequestParam(name="comentario")String comentario,
            RedirectAttributes redirectAttributes) {
        
        if(formServiceImpl.procesarFormContacto(nombre, telefono, comentario)) {
            return "redirect:/contacto";
        } else {
            //redirectAttributes.addFlashAttribute("mensajeError", "Parece que ha ocurrido un error, por favor intentelo de nuevo en unos minutos.");
            return "redirect:/contacto";
        }
        
    }
    
    
    /**
     * Muestra el formulario para modificar una reserva.
     *
     * @param id el ID de la reserva
     * @param email el email del cliente
     * @param redirectAttributes atributos para redireccionamiento
     * @return la redirección a la página de gestión de reservas o a la de reservas si no se encuentra la reserva
     */
    @PostMapping(value="/modificar")
    public String mostrarFormModificarReserva(@RequestParam(name="id")String id,
            @RequestParam(name="email")String email, RedirectAttributes redirectAttributes) {
        ModificarReservasDto modificarReservasDto = reservaServiceImpl.buscarPorIdReservaYClienteEmail(id, email);
        if(modificarReservasDto != null) {
            return "redirect:/gestionarReservas/" + id + "/" + email;
        } else {
            return "redirect:/reservas";
        }
        
    }
    
    /**
     * Procesa el formulario para modificar una reserva.
     *
     * @param idReserva el ID de la reserva
     * @param modificarReservasDto el objeto DTO para modificar reservas
     * @return la redirección a la página de reservas
     */
    @PostMapping("/modificarReserva/{idReserva}")
    public String procesarFormModificarReserva(@PathVariable("idReserva") String idReserva, ModificarReservasDto modificarReservasDto) {
        reservaServiceImpl.modificarReserva(modificarReservasDto);
        return "redirect:/reservas";
    }
    
    @PostMapping("/eliminar/{idReserva}")
    public String eliminarReserva(@PathVariable("idReserva") String idReserva, ModificarReservasDto modificarReservasDto) {
        reservaServiceImpl.eliminarReserva(idReserva);
        return "redirect:/reservas";
    }
    
} 
