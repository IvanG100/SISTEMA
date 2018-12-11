/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO
 */
public class Creditos {
    
    private int id_aprobacioncredito;
    private Date fecha_aprobacioncredito;
    private String descripcion_aprobacioncredito;
    private String estado_aprobacioncredito;
    private String referencia_comercial;
    private String referencia_personal;
    private String limite_credito;

    public Creditos() {
    }

    public Creditos(int id_aprobacioncredito, Date fecha_aprobacioncredito, String descripcion_aprobacioncredito, String estado_aprobacioncredito, String referencia_comercial, String referencia_personal, String limite_credito) {
        this.id_aprobacioncredito = id_aprobacioncredito;
        this.fecha_aprobacioncredito = fecha_aprobacioncredito;
        this.descripcion_aprobacioncredito = descripcion_aprobacioncredito;
        this.estado_aprobacioncredito = estado_aprobacioncredito;
        this.referencia_comercial = referencia_comercial;
        this.referencia_personal = referencia_personal;
        this.limite_credito = limite_credito;
    }

    public int getId_aprobacioncredito() {
        return id_aprobacioncredito;
    }

    public void setId_aprobacioncredito(int id_aprobacioncredito) {
        this.id_aprobacioncredito = id_aprobacioncredito;
    }

    public Date getFecha_aprobacioncredito() {
        return fecha_aprobacioncredito;
    }

    public void setFecha_aprobacioncredito(Date fecha_aprobacioncredito) {
        this.fecha_aprobacioncredito = fecha_aprobacioncredito;
    }

    public String getDescripcion_aprobacioncredito() {
        return descripcion_aprobacioncredito;
    }

    public void setDescripcion_aprobacioncredito(String descripcion_aprobacioncredito) {
        this.descripcion_aprobacioncredito = descripcion_aprobacioncredito;
    }

    public String getEstado_aprobacioncredito() {
        return estado_aprobacioncredito;
    }

    public void setEstado_aprobacioncredito(String estado_aprobacioncredito) {
        this.estado_aprobacioncredito = estado_aprobacioncredito;
    }

    public String getReferencia_comercial() {
        return referencia_comercial;
    }

    public void setReferencia_comercial(String referencia_comercial) {
        this.referencia_comercial = referencia_comercial;
    }

    public String getReferencia_personal() {
        return referencia_personal;
    }

    public void setReferencia_personal(String referencia_personal) {
        this.referencia_personal = referencia_personal;
    }

    public String getLimite_credito() {
        return limite_credito;
    }

    public void setLimite_credito(String limite_credito) {
        this.limite_credito = limite_credito;
    }

    
    
    
    
}
