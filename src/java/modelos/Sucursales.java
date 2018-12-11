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
public class Sucursales {
    private int id_sucursal;
    private String nombre_sucursal;

    public Sucursales() {
    }

    public Sucursales(int id_sucursal, String nombre_sucursal) {
        this.id_sucursal = id_sucursal;
        this.nombre_sucursal = nombre_sucursal;
    }

    
    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre_sucursal() {
        return nombre_sucursal;
    }

    public void setNombre_sucursal(String nombre_sucursal) {
        this.nombre_sucursal = nombre_sucursal;
    }
    
    
    
    
}
