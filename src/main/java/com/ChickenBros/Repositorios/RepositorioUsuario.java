
package com.ChickenBros.Repositorios;

import com.ChickenBros.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
	
	@Query("SELECT a from Usuario a WHERE a.email LIKE :email")
	public Usuario buscarPorEmail(@Param("email") String email);	
	
	@Query("SELECT a from Usuario a WHERE a.nombre LIKE :nombre")
	public Usuario buscarPorNombre(@Param("nombre") String email);	
}
