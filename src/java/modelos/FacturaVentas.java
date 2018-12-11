/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

public class FacturaVentas {

    private int id_factura_venta;
    
    private Clientes cliente;
    private Date fecha_factura_venta;
    private Tipo_facturas tipofactura;
    
    private int cantidad_cuotas;
    
    private int numero_factura_venta;
    private int total;
    private String estado_venta;
    private Timbrados timbrado;
    private Usuarios usuario;
    


    public FacturaVentas() {
    }

    public FacturaVentas(int id_factura_venta, Clientes cliente, Date fecha_factura_venta, Tipo_facturas tipofactura, int cantidad_cuotas, int numero_factura_venta, int total, String estado_venta, Timbrados timbrado, Usuarios usuario) {
        this.id_factura_venta = id_factura_venta;
        this.cliente = cliente;
        this.fecha_factura_venta = fecha_factura_venta;
        this.tipofactura = tipofactura;
        this.cantidad_cuotas = cantidad_cuotas;
        this.numero_factura_venta = numero_factura_venta;
        this.total = total;
        this.estado_venta = estado_venta;
        this.timbrado = timbrado;
        this.usuario = usuario;
    }

    public int getId_factura_venta() {
        return id_factura_venta;
    }

    public void setId_factura_venta(int id_factura_venta) {
        this.id_factura_venta = id_factura_venta;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_factura_venta() {
        return fecha_factura_venta;
    }

    public void setFecha_factura_venta(Date fecha_factura_venta) {
        this.fecha_factura_venta = fecha_factura_venta;
    }

    public Tipo_facturas getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(Tipo_facturas tipofactura) {
        this.tipofactura = tipofactura;
    }

    public int getCantidad_cuotas() {
        return cantidad_cuotas;
    }

    public void setCantidad_cuotas(int cantidad_cuotas) {
        this.cantidad_cuotas = cantidad_cuotas;
    }

    public int getNumero_factura_venta() {
        return numero_factura_venta;
    }

    public void setNumero_factura_venta(int numero_factura_venta) {
        this.numero_factura_venta = numero_factura_venta;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEstado_venta() {
        return estado_venta;
    }

    public void setEstado_venta(String estado_venta) {
        this.estado_venta = estado_venta;
    }

    public Timbrados getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Timbrados timbrado) {
        this.timbrado = timbrado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    

    

    
    

    

    

    

    

    

    
}
