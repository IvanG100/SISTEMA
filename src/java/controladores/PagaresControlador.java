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
import modelos.Pagares;
import utiles.Conexion;


/**
 *
 * @author alumno
 */
public class PagaresControlador {
    public static boolean agregar(Pagares pagare){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into pagares (id_factura_venta, montoenletras)" 
                    + "values ('" + pagare.getVentas().getId_factura_venta() + "','"
                    + pagare.getMontoenletras() + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_pagare = keyset.getInt(1);
                    pagare.setId_pagare(id_pagare);
                   
                }
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(PagaresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    
}
