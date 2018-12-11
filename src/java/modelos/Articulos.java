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
public class Articulos {
    private int id_articulo;
    private String nombre_articulo;
    private Marcas marca;
    private Categorias categoria;
    private String descripcion_articulo;
    private int precio_venta;
    private int precio_compra;
    private String codigo_articulo;
    private int iva_articulo;
    private Ubicaciones ubicacion;

    public Articulos() {
    }

    public Articulos(int id_articulo, String nombre_articulo, Marcas marca, Categorias categoria, String descripcion_articulo, int precio_venta, int precio_compra, String codigo_articulo, int iva_articulo, Ubicaciones ubicacion) {
        this.id_articulo = id_articulo;
        this.nombre_articulo = nombre_articulo;
        this.marca = marca;
        this.categoria = categoria;
        this.descripcion_articulo = descripcion_articulo;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.codigo_articulo = codigo_articulo;
        this.iva_articulo = iva_articulo;
        this.ubicacion = ubicacion;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion_articulo() {
        return descripcion_articulo;
    }

    public void setDescripcion_articulo(String descripcion_articulo) {
        this.descripcion_articulo = descripcion_articulo;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public int getIva_articulo() {
        return iva_articulo;
    }

    public void setIva_articulo(int iva_articulo) {
        this.iva_articulo = iva_articulo;
    }

    public Ubicaciones getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicaciones ubicacion) {
        this.ubicacion = ubicacion;
    }

    

    

    

    

    

    
}
