package uhmami.modelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.entities.Mesa;
import uhmami.modelo.serviceImpl.ReservaServiceImpl;

/**
 * Controlador para manejar las peticiones GET de la aplicación.
 */
@Controller
public class GetController {
    
    @Autowired
    private ReservaServiceImpl reservaServiceImpl;

    /**
     * Muestra la página de inicio.
     *
     * @return el nombre de la vista "home"
     */
    @GetMapping("/")
    public String mostrarHome() {
        return "home";
    }

    /**
     * Muestra la página del restaurante.
     *
     * @return el nombre de la vista "restaurante"
     */
    @GetMapping("/restaurante")
    public String mostrarRestaurante() {
        return "restaurante";
    }

    /**
     * Muestra la página de la carta del restaurante.
     *
     * @return el nombre de la vista "carta"
     */
    @GetMapping("/carta")
    public String mostrarCarta() {
        return "carta";
    }

    /**
     * Muestra la página de reservas.
     *
     * @param model el modelo de datos
     * @return el nombre de la vista "reservas"
     */
    @GetMapping("/reservas")
    public String mostrarReservas(Model model) {
        model.addAttribute("reservaDto", new ReservaDto());
        model.addAttribute("mesa", new Mesa());
        return "reservas";
    }

    /**
     * Muestra la página de contacto.
     *
     * @param model el modelo de datos
     * @return el nombre de la vista "contacto"
     */
    @GetMapping("/contacto")
    public String mostrarContacto(Model model) {
        model.addAttribute("consulta", new Consulta());
        return "contacto";
    }

    /**
     * Muestra la página de inicio de sesión.
     *
     * @return el nombre de la vista "login"
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Muestra la página de listado de reservas. Acceso restringido a usuarios con rol "ADMINISTRADOR".
     *
     * @return el nombre de la vista "listadoReservas"
     */
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/listadoReservas")
    public String listado() {
        return "listadoReservas";
    }

    /**
     * Muestra la página para gestionar una reserva específica.
     *
     * @param idReserva el ID de la reserva
     * @param email el email del cliente
     * @param model el modelo de datos
     * @return el nombre de la vista "gestionarReservas"
     */
    @GetMapping("/gestionarReservas/{idReserva}/{email}")
    public String gestionarReservas(@PathVariable("idReserva") String idReserva, @PathVariable("email") String email, Model model) {
        ModificarReservasDto modificarReservasDto = reservaServiceImpl.buscarPorIdReservaYClienteEmail(idReserva, email);
        model.addAttribute("modificarReservasDto", modificarReservasDto);
        return "gestionarReservas";
    }
    
    /**
     * Maneja las solicitudes GET para la ruta "/error".
     *
     * @return el nombre de la vista "error404".
     */
    @GetMapping("/error404")
    public String mostrarError() {
    	return "error404";
    }
    
    /**
     * Maneja las solicitudes GET para la ruta "/enConstruccion".
     *
     * @return el nombre de la vista "enConstruccion".
     */
    @GetMapping("/enConstruccion")
    public String mostrarEnConstruccion() {
    	return "enConstruccion";
    }
    
    /**
     * Maneja las solicitudes GET para la ruta "/hamburguesa".
     *
     * @return el nombre de la vista "menu_hamburguesa".
     */
    @GetMapping("/hamburguesa")
    public String mostrarMenuHamburguesa() {
    	return "menu_hamburguesa";
    }
    
    /**
     * Maneja la eliminación de una reserva por su ID.
     *
     * Este método está mapeado a la solicitud GET a "/eliminar/{idReserva}". Elimina la reserva
     * con el ID dado y redirige al usuario a la página de reservas.
     *
     * @param idReserva el ID de la reserva que se va a eliminar.
     * @return una cadena de redirección a la página de reservas.
     */
    @GetMapping("/eliminar/{idReserva}")
    public String eliminarReserva(@PathVariable("idReserva") String idReserva) {
        reservaServiceImpl.eliminarReserva(idReserva);
        return "redirect:/reservas";
    }
}
