package com.ChickenBros.Repositorios;

import Entidad.Admin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdminRepositorio extends JpaRepository <Admin, String>{

//	@Query("SELECT a from Admin a WHERE a.idAdmin = :idAdmin ")
//	public List<Admin> buscarId(@Param("idAdmin") String idAdmin);
}
