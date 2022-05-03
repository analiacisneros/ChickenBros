
package com.ChickenBros.Servicios;

import com.ChickenBros.Entidades.Producto;
import com.ChickenBros.Repositorios.RepositorioProducto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class ServicioProducto {
    
    @Autowired
    private RepositorioProducto productoRepositorio;
    
    public void validaciones(String nombre, String descripcion, Integer precio, String imagen) throws Exception { 
        if (nombre == null || nombre.isEmpty()) {
        throw new Exception("Es necesario colocar el nombre del producto");
        } 
         if (descripcion == null || descripcion.isEmpty()) {
        throw new Exception("Es necesario colocar la descripcion del producto");
        }
          if (precio == null || precio < 0) {
        throw new Exception("El precio no puede ser menor a cero o nulo ");
        }
           if (imagen == null || imagen.isEmpty()) {
        throw new Exception("Es necesario colocar una imagen del producto");
        }
    }
    
    
    
    
    public List<Producto> listarProducto(){
         return productoRepositorio.findAll();     
    }
    
    
    
    
   @Transactional() 
    public void agregarProducto(String nombre, String descripcion, Integer precio, String imagen) throws Exception {
      validaciones(nombre, descripcion ,precio , imagen);
      
      Producto productoNuevo = new Producto();
        
      productoNuevo.setNombre(nombre);
      productoNuevo.setDescripcion(descripcion);
      productoNuevo.setPrecio(precio);
      productoNuevo.setImagen(imagen);
      productoNuevo.setHabilitado(true);
    
      productoRepositorio.save(productoNuevo);
    }
    
    
    
    
    @Transactional()
    public void modificarProducto(String id, String nombre, String descripcion, Integer precio, String imagen) throws Exception {
     validaciones(nombre, descripcion ,precio , imagen);
     Optional <Producto> respuesta = productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
        Producto productoNuevo = respuesta.get();
        
         productoNuevo.setNombre(nombre);
         productoNuevo.setDescripcion(descripcion);
         productoNuevo.setPrecio(precio);
         productoNuevo.setImagen(imagen);
         productoNuevo.setHabilitado(true);
         
      productoRepositorio.save(productoNuevo);
    } else {
            throw new Exception("No se encontro el ID solicitado");
        }
    }
    
    
    
    
    @Transactional()
    public void eliminarProducto(String id) throws Exception {
     Optional <Producto> respuesta = productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            productoRepositorio.deleteById(id);
        }else {
            throw new Exception("No se encontro el ID solicitado");
        }
    }
    
    
    @Transactional
    public Producto getById(String id) throws Exception{
        if (productoRepositorio.getById(id) == null) {
            throw new Exception("No se pudo encontrar la editorial con ese ID.");
        }
        return productoRepositorio.getById(id);
    }
    
}
