/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChickenBros.Repositorios;


import Entidad.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ClienteRepositorio extends JpaRepository <Cliente, String>{

	@Query("SELECT c from Cliente c WHERE c.direccion = :direccion ")
	public List<Cliente> buscarDireccion(@Param("direccion")String direccion);
	
}
