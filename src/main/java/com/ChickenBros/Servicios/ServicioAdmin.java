package com.ChickenBros.Servicios;

import Entidad.Admin;
import Entidad.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Repositorio.AdminRepositorio;


@Service
public class AdminServicio extends Usuario{

    	@Autowired
	private AdminRepositorio adminRepo;

	@Transactional
	public Admin guardar(String id) throws Exception {
            Admin entidad = new Admin();
            Admin entidad = new Admin();          
//            entidad.setIdAdmin(id);
            return adminRepo.save(entidad);
	}

//	@Transactional(readOnly = true)
//	public List<Admin> listarId(String idAdmin) {
//            return adminRepo.buscarId(idAdmin);
//	}

	@Transactional(readOnly = true)
	public List<Admin> listarTodos() {
            return adminRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Admin> listarTodos() {
            return adminRepo.findAll();
	}
}
