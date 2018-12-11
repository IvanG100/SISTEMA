/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Recibos;
import modelos.CuentasClientes;
import utiles.Conexion;


/**
 *
 * @author alumno
 */
public class RecibosControlador {
    public static boolean agregar(Recibos recibo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into recibos (id_cuenta, montoenletras)" 
                    + "values ('" + recibo.getCuentas().getId_cuenta() + "','"
                    + recibo.getMontoenletras() + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_recibo = keyset.getInt(1);
                    recibo.setId_recibo(id_recibo);
                   
                }
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(RecibosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    
}
