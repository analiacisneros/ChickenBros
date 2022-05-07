package com.ChickenBros.Servicios;

import com.ChickenBros.Entidades.Admin;
import com.ChickenBros.Entidades.Rol;
import com.ChickenBros.Repositorios.AdminRepositorio;
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
public class AdminServicio implements UserDetailsService{

    	@Autowired
	private AdminRepositorio adminRepo;

	@Transactional
	public Admin guardar(String nombre, String apellido, String email, String clave) throws Exception {
            Admin entidad = new Admin();   

		entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));
		entidad.setRol(Rol.ADMIN);
            
            return adminRepo.save(entidad);
	}
        
        public void modificarAdmin (String id,String nombre, String apellido, String email, String clave) throws Exception {
                                 
            Optional<Admin> respuesta = adminRepo.findById(id);
            if(respuesta.isPresent()){
            Admin entidad = respuesta.get();
            
                entidad.setNombre(nombre);
		entidad.setApellido(apellido);
		entidad.setEmail(email);
		entidad.setClave(new BCryptPasswordEncoder().encode(clave));

            } else{
                throw new Error("No se encontro el usuario");
            }
        }

	@Transactional(readOnly = true)
	public List<Admin> listarId(String email) {
            return adminRepo.buscarEmail(email);
	}

	@Transactional(readOnly = true)
	public List<Admin> listarTodos() {
            return adminRepo.findAll();
	}

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        		
		Admin admin = adminRepo.getById(id);
		
		if (admin != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
                        
			GrantedAuthority p = new SimpleGrantedAuthority("ADMIN");
			permisos.add(p);
                        
                        User cliente = new User(admin.getEmail(), admin.getClave(), permisos);
                        return cliente;
		} else{                
		return null;}
    }
}
