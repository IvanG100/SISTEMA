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
public class Timbrados {
    private int id_timbrado;
    private String numero_timbrado;
    private Date fecha_inicio_timbrado;
    private Date fecha_vencimiento_timbrado;
    private Date fecha_actual_timbrado;
    private int desde_timbrado;
    private int hasta_timbrado;
    private String estado_timbrado;
    private Puestos puesto;
    private Establecimientos establecimiento;

    public Timbrados() {
    }

    public Timbrados(int id_timbrado, String numero_timbrado, Date fecha_inicio_timbrado, Date fecha_vencimiento_timbrado, Date fecha_actual_timbrado, int desde_timbrado, int hasta_timbrado, String estado_timbrado, Puestos puesto, Establecimientos establecimiento) {
        this.id_timbrado = id_timbrado;
        this.numero_timbrado = numero_timbrado;
        this.fecha_inicio_timbrado = fecha_inicio_timbrado;
        this.fecha_vencimiento_timbrado = fecha_vencimiento_timbrado;
        this.fecha_actual_timbrado = fecha_actual_timbrado;
        this.desde_timbrado = desde_timbrado;
        this.hasta_timbrado = hasta_timbrado;
        this.estado_timbrado = estado_timbrado;
        this.puesto = puesto;
        this.establecimiento = establecimiento;
    }

    public int getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(int id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public String getNumero_timbrado() {
        return numero_timbrado;
    }

    public void setNumero_timbrado(String numero_timbrado) {
        this.numero_timbrado = numero_timbrado;
    }

    public Date getFecha_inicio_timbrado() {
        return fecha_inicio_timbrado;
    }

    public void setFecha_inicio_timbrado(Date fecha_inicio_timbrado) {
        this.fecha_inicio_timbrado = fecha_inicio_timbrado;
    }

    public Date getFecha_vencimiento_timbrado() {
        return fecha_vencimiento_timbrado;
    }

    public void setFecha_vencimiento_timbrado(Date fecha_vencimiento_timbrado) {
        this.fecha_vencimiento_timbrado = fecha_vencimiento_timbrado;
    }

    public Date getFecha_actual_timbrado() {
        return fecha_actual_timbrado;
    }

    public void setFecha_actual_timbrado(Date fecha_actual_timbrado) {
        this.fecha_actual_timbrado = fecha_actual_timbrado;
    }

    public int getDesde_timbrado() {
        return desde_timbrado;
    }

    public void setDesde_timbrado(int desde_timbrado) {
        this.desde_timbrado = desde_timbrado;
    }

    public int getHasta_timbrado() {
        return hasta_timbrado;
    }

    public void setHasta_timbrado(int hasta_timbrado) {
        this.hasta_timbrado = hasta_timbrado;
    }

    public String getEstado_timbrado() {
        return estado_timbrado;
    }

    public void setEstado_timbrado(String estado_timbrado) {
        this.estado_timbrado = estado_timbrado;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }

    

    
    
    
}
