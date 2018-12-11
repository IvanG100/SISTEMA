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
public class Recibos {
    private int id_recibo;
    CuentasClientes cuentas;
    private String montoenletras;

    public Recibos() {
    }

    public Recibos(int id_recibo, CuentasClientes cuentas, String montoenletras) {
        this.id_recibo = id_recibo;
        this.cuentas = cuentas;
        this.montoenletras = montoenletras;
    }

    public int getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(int id_recibo) {
        this.id_recibo = id_recibo;
    }

    public CuentasClientes getCuentas() {
        return cuentas;
    }

    public void setCuentas(CuentasClientes cuentas) {
        this.cuentas = cuentas;
    }

    public String getMontoenletras() {
        return montoenletras;
    }

    public void setMontoenletras(String montoenletras) {
        this.montoenletras = montoenletras;
    }

    
    
}
