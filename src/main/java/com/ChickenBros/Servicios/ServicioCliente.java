
package com.ChickenBros.Servicios;

import com.ChickenBros.Entidades.Cliente;
import com.ChickenBros.Repositorio.ClienteRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class ServicioCliente { //implements UserDetailsService {
	
    @Autowired
	private ClienteRepositorio clienteRepo;

	@Transactional
	public Cliente guardar(String nombre, String apellido, String email, String direccion, String clave, Long tel) throws Exception {

                validar(nombre, apellido, direccion, email, clave, clave);
                
                Cliente entidad = new Cliente();
                    
                entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));
                entidad.setDireccion(direccion);
                entidad.setTelefono(tel);
                
                return clienteRepo.save(entidad);
	}

        public void modificarCliente (String id,String nombre, String apellido, String email, String clave,Long tel, String direccion) throws Exception {
                        
            validar(nombre, apellido, direccion, email, clave, clave);
            
            Optional<Cliente> respuesta = clienteRepo.findById(id);
           
            if(respuesta.isPresent()){
            Cliente entidad = respuesta.get();
            
                entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));
                entidad.setTelefono(tel);
                entidad.setDireccion(direccion);
            } else{
                throw new Error("No se encontro el usuario");
            }
        }
        
	@Transactional(readOnly = true)
	public List<Cliente> listarPorDireccion(String Direccion) {
                return clienteRepo.buscarDireccion(Direccion);
	}

	@Transactional(readOnly = true)
	public List<Cliente> listarTodos() {
                return clienteRepo.findAll();
	}

	public void validar(String nombre,String apellido, String direccion, String email, String clave, String clave2) throws Exception {

		if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe tener un nombre valido");
		}

                if (apellido == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe tener un nombre valido");
		}

                if (direccion == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe tener un nombre valido");
		}

		if (email == null || email.isEmpty() || email.contains("  ")) {
			throw new Exception("Debe tener un email valido");
		}

		if (clienteRepo.buscarPorEmail(email) != null) {
			throw new Exception("El Email ya esta en uso");
		}

		if (clave == null || clave.isEmpty() || clave.contains("  ") || clave.length() < 8 || clave.length() > 12) {
			throw new Exception("Debe tener una clave valida");
		}
                if (clave.equals(clave2)) {
                throw new Error("Las claves no son iguales");
            }
	}
        
//        @Override
//        public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		
//		Cliente user = clienteRepo.getById(id);
//		
//		if (user != null) {
//			List<GrantedAuthority> permisos = new ArrayList<>();
//                        
//			GrantedAuthority p = new SimpleGrantedAuthority("CLIENTE");
//			permisos.add(p);
//                        
//                        User cliente = new User(user.getEmail(), user.getClave(), permisos);
//                        return cliente;
//		} else{                
//		return null;}
//}
}
