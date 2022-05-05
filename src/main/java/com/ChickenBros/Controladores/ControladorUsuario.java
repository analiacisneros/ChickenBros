
package com.ChickenBros.Controladores;

import com.ChickenBros.Servicios.UsuarioServicio;
Import com.ChickenBors.Etidades.Usuario
import java.util.List;
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
	private ServicioUsuario usuarioServ;


	@GetMapping("/registro")
	public String formulario() {
		return "form-usuario";
	}
        
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		List<Usuario> todos = usuarioServ.listarTodos();
		   
		modelo.addAttribute("usuarios", todos);
		
		return "list-usuario";
	}
        
        //@PreAuthorize("hasAnyRole('ADMIN')") se usa para limitar el acceso
	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String email, @RequestParam String clave, @RequestParam String clave2) throws Exception {

        try{
             usuarioServ.guardar(nombre, apellido, email, clave, clave2);
        }catch (Error ex){
                modelo.put("error", ex.getMessage());
                modelo.put("nombre", nombre);
                modelo.put("apellido", apellido);
                modelo.put("email", email);
                
		return "form-usuario";
		}
                return "index.html";}

}
