package com.ChickenBros.Controladores;

import Entidad.Cliente;
import Servicio.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

	@Autowired
	private ClienteServicio clienteServ;
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		List<Cliente> todos = clienteServ.listarTodos();
		
		modelo.addAttribute("clientes", todos);
		
		return "list-cliente";
	}
	
	@GetMapping("/registro")
	public String formulario() {
		return "form-cliente";
	}
	
	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String direccion, @RequestParam Long tel) {
		
		try {
			clienteServ.guardar(tel, direccion);
			
			modelo.put("exito", "registro exitoso");
			return "form-cliente";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "form-cliente";
		}
	}
}
