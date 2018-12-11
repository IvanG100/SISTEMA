/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO
 */
public class DetallesAjustes {

    private int id_ajuste_stock_detalle;
    private Ajustes ajuste;
    private int cantidad_ajuste_stock;
    private Articulos articulo;
    //private Productos producto;

    public DetallesAjustes() {
    }

    public int getId_ajuste_stock_detalle() {
        return id_ajuste_stock_detalle;
    }

    public void setId_ajuste_stock_detalle(int id_ajuste_stock_detalle) {
        this.id_ajuste_stock_detalle = id_ajuste_stock_detalle;
    }

    public Ajustes getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajustes ajuste) {
        this.ajuste = ajuste;
    }

    public int getCantidad_ajuste_stock() {
        return cantidad_ajuste_stock;
    }

    public void setCantidad_ajuste_stock(int cantidad_ajuste_stock) {
        this.cantidad_ajuste_stock = cantidad_ajuste_stock;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    
    
    
    /*public DetallesAjustes(int id_detalleajuste, Ajustes ajuste, int cantidad_ajuste, Productos producto) {
        this.id_detalleajuste = id_detalleajuste;
        this.ajuste = ajuste;
        this.cantidad_ajuste = cantidad_ajuste;
        this.producto = producto;
    }

    public int getId_detalleajuste() {
        return id_detalleajuste;
    }

    public void setId_detalleajuste(int id_detalleajuste) {
        this.id_detalleajuste = id_detalleajuste;
    }

    public Ajustes getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajustes ajuste) {
        this.ajuste = ajuste;
    }

    public int getCantidad_ajuste() {
        return cantidad_ajuste;
    }

    public void setCantidad_ajuste(int cantidad_ajuste) {
        this.cantidad_ajuste = cantidad_ajuste;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }*/

    public DetallesAjustes(int id_ajuste_stock_detalle, Ajustes ajuste, int cantidad_ajuste_stock, Articulos articulo) {
        this.id_ajuste_stock_detalle = id_ajuste_stock_detalle;
        this.ajuste = ajuste;
        this.cantidad_ajuste_stock = cantidad_ajuste_stock;
        this.articulo = articulo;
    }
    
    
}
