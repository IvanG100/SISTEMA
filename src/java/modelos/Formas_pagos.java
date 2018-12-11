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
public class Formas_pagos {
    private int id_forma_pago;
    private String nombre_forma_pago;

    public Formas_pagos() {
    }

    public Formas_pagos(int id_forma_pago, String nombre_forma_pago) {
        this.id_forma_pago = id_forma_pago;
        this.nombre_forma_pago = nombre_forma_pago;
    }

    public int getId_forma_pago() {
        return id_forma_pago;
    }

    public void setId_forma_pago(int id_forma_pago) {
        this.id_forma_pago = id_forma_pago;
    }

    public String getNombre_forma_pago() {
        return nombre_forma_pago;
    }

    public void setNombre_forma_pago(String nombre_forma_pago) {
        this.nombre_forma_pago = nombre_forma_pago;
    }

    
    
    
    
    
}
