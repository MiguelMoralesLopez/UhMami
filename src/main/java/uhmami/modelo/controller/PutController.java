package uhmami.modelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.service.ReservaServiceImpl;

@Controller
public class PutController {
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	

}
