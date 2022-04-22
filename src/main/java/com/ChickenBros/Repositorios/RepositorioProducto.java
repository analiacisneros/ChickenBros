
package com.ChickenBros.Repositorios;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, String> {
   
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Producto buscarPorNombre(@Param("nombre") String nombre);
    
}
