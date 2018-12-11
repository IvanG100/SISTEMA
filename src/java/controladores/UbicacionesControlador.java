/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Ubicaciones;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class UbicacionesControlador {
    public static boolean agregar(Ubicaciones ubicacion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into ubicaciones (nombre_ubicacion)" 
                    + "values ('" + ubicacion.getNombre_ubicacion() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Ubicaciones ubicacion){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update ubicaciones set nombre_ubicacion='" + ubicacion.getNombre_ubicacion() + "'"
                    + " where id_ubicacion=" + ubicacion.getId_ubicacion();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Ubicaciones ubicacion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from ubicaciones where id_ubicacion=" + ubicacion.getId_ubicacion();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Ubicaciones buscarId(Ubicaciones ubicacion) {
        if (Conexion.conectar()){
            String sql = "select * from ubicaciones where id_ubicacion ='"+ubicacion.getId_ubicacion()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    ubicacion.setId_ubicacion(rs.getInt("id_ubicacion"));
                    ubicacion.setNombre_ubicacion(rs.getString("nombre_ubicacion"));
                } else {
                    ubicacion.setId_ubicacion(0);
                    ubicacion.setNombre_ubicacion("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return ubicacion;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from ubicaciones where upper(nombre_ubicacion) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_ubicacion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ubicacion") + "</td>"
                                + "<td>" + rs.getString("nombre_ubicacion") + "</td>"
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
    
    public static Ubicaciones buscarUbicacion(Ubicaciones ubicacion) {
        if (Conexion.conectar()){
            String sql = "select * from ubicaciones where nombre_ubicacion ='"+ubicacion.getNombre_ubicacion()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    ubicacion.setId_ubicacion(0);
                    
                } else {
                    ubicacion.setId_ubicacion(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return ubicacion;
    }
}
