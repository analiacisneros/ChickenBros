package com.ChickenBros.Controladores;

import com.ChickenBros.Entidades.Cliente;
import com.ChickenBros.Servicios.ServicioCliente;
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
	private ServicioCliente clienteServ;
	
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
	public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String email, @RequestParam String direccion, @RequestParam Long tel, @RequestParam String clave, @RequestParam String clave2) {
		
		try {
			clienteServ.guardar(nombre, apellido, email, clave, clave2, email, tel, direccion);
			
			modelo.put("exito", "registro exitoso");
			return "form-cliente";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "form-cliente";
		}
	}
}
