package com.ChickenBros.Servicios;

import com.Pollo.demo.src.Entidad.Cliente;
import com.Pollo.demo.src.Entidad.Usuario;
import com.Pollo.demo.src.Repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepo;

	@Transactional
	public Usuario guardar(String nombre, String apellido, String email, String clave) throws Exception {

		validar(nombre, apellido, email, clave);

		Usuario entidad = new Usuario();

		entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));

		return usuarioRepo.save(entidad);
	}
        
        @Transactional(readOnly = true)
	public List<Usuario> listarTodos() {
                return usuarioRepo.findAll();
	}

	public void validar(String nombre, String apellido, String email, String clave) throws Exception {

		if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe tener un nombre valido");
		}

		if (apellido == null || apellido.isEmpty() || apellido.contains("  ")) {
			throw new Exception("Debe tener un apellido valido");
		}


		if (email == null || email.isEmpty() || email.contains("  ")) {
			throw new Exception("Debe tener un email valido");
		}

		if (usuarioRepo.buscarPorEmail(email) != null) {
			throw new Exception("El Email ya esta en uso");
		}

		if (clave == null || clave.isEmpty() || clave.contains("  ") || clave.length() < 8 || clave.length() > 12) {
			throw new Exception("Debe tener una clave valida");
		}
	}

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		
//		Usuario user = usuarioRepo.buscarPorEmail(email);
//		
//		if (user != null) {
//			List<GrantedAuthority> permissions = new ArrayList<>();
//			GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());
//			permissions.add(p);
//			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//			HttpSession session = attr.getRequest().getSession(true);
//			session.setAttribute("usuario", user);
//			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getClave(),
//					permissions);
//		}
//		return null;
//
//	}
}
