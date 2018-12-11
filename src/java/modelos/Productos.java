/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Productos {
    private int id_producto;
    private String nombre_producto;
    private String marca_producto;
    private String descripcion_producto;
    private String cantidad_producto;
    private String precio_producto;
    private Proveedores proveedor;

    public Productos() {
    }

    public Productos(int id_producto, String nombre_producto, String marca_producto, String descripcion_producto, String cantidad_producto, String precio_producto, Proveedores proveedor) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.marca_producto = marca_producto;
        this.descripcion_producto = descripcion_producto;
        this.cantidad_producto = cantidad_producto;
        this.precio_producto = precio_producto;
        this.proveedor = proveedor;
    }

    

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getMarca_producto() {
        return marca_producto;
    }

    public void setMarca_producto(String marca_producto) {
        this.marca_producto = marca_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(String cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public String getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(String precio_producto) {
        this.precio_producto = precio_producto;
    }
    
    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }
    
    
}
