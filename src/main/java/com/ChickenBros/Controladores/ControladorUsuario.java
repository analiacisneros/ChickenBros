
package com.ChickenBros.Controladores;

import Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServ;


	@GetMapping("/registro")
	public String formulario() {
		return "form-usuario";
	}

	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String email, @RequestParam String clave) {

		try {
			usuarioServ.guardar(nombre, apellido, email, clave);

			modelo.put("exito", "registro exitoso");
			return "form-usuario";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "form-usuario";
		}
	}

}
