package com.ChickenBros.Servicios;

import com.ChickenBros.Entidades.Rol;
import com.ChickenBros.Entidades.Cliente;
import com.ChickenBros.Entidades.Usuario;
import com.ChickenBros.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioUsuario implements UserDetailsService   {

	@Autowired
	private UsuarioRepositorio usuarioRepo;

	@Transactional
	public Usuario guardar(String nombre, String apellido, String email, String direccion, Long tel, String clave, String clave2) throws Exception {

		validar(nombre, apellido, email, clave, clave2);

		Cliente entidad = new Cliente();

		entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
                entidad.setRol(Rol.CLIENTE);
                entidad.setDireccion(direccion);
                entidad.setTelefono(tel);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));

		return usuarioRepo.save(entidad);
	}
        
        @Transactional(readOnly = true)
	public List<Usuario> listarTodos() {
                return usuarioRepo.findAll();
	}

	public void validar(String nombre, String apellido, String email, String clave, String clave2) throws Exception {

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
                if (clave.equals(clave2)) {
                throw new Error("Las claves no son iguales");
            }
	}

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
  		Usuario user = usuarioRepo.getById(id);
		
		if (user != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
                        
			GrantedAuthority p = new SimpleGrantedAuthority("CLIENTE");
			permisos.add(p);
                        
                        User cliente = new User(user.getEmail(), user.getClave(), permisos);
                        return cliente;
		} else{                
		return null;}

}}
