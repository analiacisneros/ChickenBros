
package com.ChickenBros.Controladores;

import com.ChickenBros.Entidades.Producto;
import com.ChickenBros.Repositorios.RepositorioProducto;
import com.ChickenBros.Servicios.ServicioProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productos")
public class ControladorProducto {
    
    @Autowired
    RepositorioProducto repoProducto;
    
     @Autowired
    ServicioProducto servProducto;
    
 //   LISTADO DE PRODUCTOS
    @GetMapping("/listadoProductos")
    public String productos(ModelMap modelo) {
        List<Producto> listaProductos = servProducto.listarProducto();
        modelo.put("listaProducto", listaProductos);
        return "listadoProductos.html";
    }
    
    // VISTA DE FORMULARIO PARA AGREGAR PRODUCTO
    @GetMapping("/agregarProducto")
    public String agregarProducto(){
        return "agregarProducto.html";
    }
  
    @PostMapping("/agregarProducto")
    public String registrarProducto(ModelMap modelo,@RequestParam String nombre,@RequestParam String descripcion, @RequestParam Integer precio, @RequestParam String imagen ) {
        try {
            servProducto.agregarProducto(nombre, descripcion, precio,imagen);
            modelo.put("exito", "Guardado con exito");
            List<Producto> productos = servProducto.listarProducto();
            modelo.put("productos", productos);
            return "agregarProducto.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "agregarProducto.html";
        }
    }


//EDITAR
    
    @GetMapping("/modificar/{id}")
    public String modificarProducto(@PathVariable String id, ModelMap modelo) throws Exception {
        modelo.put("producto", servProducto.getById(id));
        return "modificarProducto.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarProducto(ModelMap modelo, @PathVariable String id, @RequestParam String nombre,@RequestParam String descripcion, @RequestParam Integer precio, @RequestParam String imagen) throws Exception {
        try {
            servProducto.modificarProducto(id,nombre,descripcion, precio,imagen);
            return "redirect:/productos/listadoProductos";
        } catch (Exception e) {
            modelo.put("error", "No se pudo modificar el producto");
            modelo.put("producto", servProducto.getById(id));
            return "modificarProducto.html";
        }
    }
    
    // eliminar
     @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id){
        try {
            servProducto.eliminarProducto(id);
            
            return "redirect:/productos/listadoProductos";
        } catch (Exception e) {
            return "redirect:/productos/listadoProductos";
        }
    }
  
}
