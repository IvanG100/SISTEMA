/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Sucursales;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SucursalesControlador {
    public static boolean agregar(Sucursales sucursal){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into sucursales (nombre_sucursal)" 
                    + "values ('" + sucursal.getNombre_sucursal() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(SucursalesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Sucursales sucursal){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update sucursales set nombre_sucursal='" + sucursal.getNombre_sucursal() + "'"
                    + " where id_sucursal=" + sucursal.getId_sucursal();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(SucursalesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Sucursales sucursal){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from sucursales where id_sucursal=" + sucursal.getId_sucursal();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(SucursalesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Sucursales buscarId(Sucursales sucursal) {
        if (Conexion.conectar()){
            String sql = "select * from sucursales where id_sucursal ='"+sucursal.getId_sucursal()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    sucursal.setId_sucursal(rs.getInt("id_sucursal"));
                    sucursal.setNombre_sucursal(rs.getString("nombre_sucursal"));
                } else {
                    sucursal.setId_sucursal(0);
                    sucursal.setNombre_sucursal("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return sucursal;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from sucursales where upper(nombre_sucursal) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_sucursal offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_sucursal") + "</td>"
                                + "<td>" + rs.getString("nombre_sucursal") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static Sucursales buscarSucursal(Sucursales sucursal) {
        if (Conexion.conectar()){
            String sql = "select * from sucursales where nombre_sucursal ='"+sucursal.getNombre_sucursal()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    sucursal.setId_sucursal(0);
                    
                } else {
                    sucursal.setId_sucursal(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return sucursal;
    }
}
