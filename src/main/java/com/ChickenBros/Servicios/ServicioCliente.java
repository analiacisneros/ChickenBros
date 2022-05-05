
package com.ChickenBros.Servicios;

import com.ChickenBros.Entidades.Cliente;
import com.ChickenBros.Enum.Rol;
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
public class ServicioCliente implements UserDetailsService {
	@Autowired
	private ClienteRepositorio clienteRepo;

	@Transactional
	public Cliente guardar(String nombre, String apellido, String email, String clave, String rol, Long tel, String direccion) throws Exception {

                validar(tel, direccion, rol);
                
                Cliente entidad = new Cliente();
                    
                entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));
		entidad.setRol(Rol.CLIENTE);
                entidad.setDireccion(direccion);
                entidad.setTelefono(tel);
                
                return clienteRepo.save(entidad);
	}

        public void modificarCliente (String id,String nombre, String apellido, String email, String clave,Long tel, String direccion) throws Exception {
                        
            validar(tel, direccion, id);
            
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

	public void validar(Long tel, String direccion, String rol) throws Exception {
		
                if (tel == null) {
			throw new Exception("Telefono invalido");
		}
		
		if (direccion == null || direccion.isEmpty() || direccion.contains("  ")) {
			throw new Exception("Direccion invalida");
		}
                if (!Rol.ADMIN.toString().equals(rol) && !Rol.CLIENTE.toString().equals(rol)) {
			throw new Exception("Debe tener rol valido");
		}
		}
        
        @Override
        public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		Cliente user = clienteRepo.getById(id);
		
		if (user != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
                        
			GrantedAuthority p = new SimpleGrantedAuthority("CLIENTE");
			permisos.add(p);
                        
                        User cliente = new User(user.getEmail(), user.getClave(), permisos);
                        return cliente;
		} else{                
		return null;}
}
}
