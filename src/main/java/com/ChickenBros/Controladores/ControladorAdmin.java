package com.ChickenBros.Controladores;

import Entidad.Admin;
import Servicio.AdminServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

	@Autowired
	private AdminServicio adminServ;
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		List<Admin> todos = adminServ.listarTodos();
		
		modelo.addAttribute("admins", todos);
		
		return "list-admin";
	}
	
	@GetMapping("/registro")
	public String formulario() {
		return "form-admin";
	}
	
	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String id) {
		
		try {
			adminServ.guardar(id);
			
			modelo.put("exito", "registro exitoso");
			return "form-admin";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "form-admin";}
	}
}
	
