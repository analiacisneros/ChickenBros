package com.ChickenBros.Repositorios;

import com.ChickenBros.Entidades.Admin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdminRepositorio extends JpaRepository <Admin, String>{
  
	@Query("SELECT a from Admin a WHERE a.email = :email ")
	public List<Admin> buscarEmail(@Param("email") String idAdmin);
}
