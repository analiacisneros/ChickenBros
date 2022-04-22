
package com.ChickenBros.Entidades;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id_pedido;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String imagen;
    private Boolean habilitado;

    public Producto() {
    }

    public Producto(String id_pedido, String nombre, String descripcion, Integer precio, String imagen, Boolean habilitado) {
        this.id_pedido = id_pedido;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.habilitado = habilitado;
    }

    public String getId_pedido() {
        return id_pedido;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
       
}
