/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author alumno
 */
public class CuentasClientesDetalle {
    private int id_detallecuenta;
    private CuentasClientes cuentasclientes;
    private int nro_cuota;
    private Date vencimiento_cuota;
    private int monto_cuota;
    private String estado_cuota;
    

    public CuentasClientesDetalle() {
    }

    public CuentasClientesDetalle(int id_detallecuenta, CuentasClientes cuentasclientes, int nro_cuota, Date vencimiento_cuota, int monto_cuota, String estado_cuota) {
        this.id_detallecuenta = id_detallecuenta;
        this.cuentasclientes = cuentasclientes;
        this.nro_cuota = nro_cuota;
        this.vencimiento_cuota = vencimiento_cuota;
        this.monto_cuota = monto_cuota;
        this.estado_cuota = estado_cuota;
    }

    public int getId_detallecuenta() {
        return id_detallecuenta;
    }

    public void setId_detallecuenta(int id_detallecuenta) {
        this.id_detallecuenta = id_detallecuenta;
    }

    public CuentasClientes getCuentasclientes() {
        return cuentasclientes;
    }

    public void setCuentasclientes(CuentasClientes cuentasclientes) {
        this.cuentasclientes = cuentasclientes;
    }

    public int getNro_cuota() {
        return nro_cuota;
    }

    public void setNro_cuota(int nro_cuota) {
        this.nro_cuota = nro_cuota;
    }

    public Date getVencimiento_cuota() {
        return vencimiento_cuota;
    }

    public void setVencimiento_cuota(Date vencimiento_cuota) {
        this.vencimiento_cuota = vencimiento_cuota;
    }

    public int getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(int monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public String getEstado_cuota() {
        return estado_cuota;
    }

    public void setEstado_cuota(String estado_cuota) {
        this.estado_cuota = estado_cuota;
    }

    

    

    

    
    
    
}
