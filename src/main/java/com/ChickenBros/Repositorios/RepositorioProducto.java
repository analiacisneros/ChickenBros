
package com.ChickenBros.Repositorios;

import com.ChickenBros.Entidades.Producto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, String> {
   
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Producto buscarPorNombre(@Param("nombre") String nombre);

    
    
}
