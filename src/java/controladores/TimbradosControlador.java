/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Timbrados;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Establecimientos;
import modelos.Puestos;

public class TimbradosControlador {
    public static boolean agregar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into timbrados (numero_timbrado, fecha_inicio_timbrado, fecha_vencimiento_timbrado, desde_timbrado, hasta_timbrado, estado_timbrado, id_puesto, id_establecimiento)" 
                    + "values ('" + timbrado.getNumero_timbrado() + "','"
                    + timbrado.getFecha_inicio_timbrado() + "','"
                    + timbrado.getFecha_vencimiento_timbrado() + "','"
                    //+ timbrado.getFecha_actual_timbrado() + "','"
                    + timbrado.getDesde_timbrado() + "','"
                    + timbrado.getHasta_timbrado() + "','"
                    + timbrado.getEstado_timbrado() + "','"
                    + timbrado.getPuesto().getId_puesto() + "','"
                    + timbrado.getEstablecimiento().getId_establecimiento() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update timbrados set numero_timbrado='" + timbrado.getNumero_timbrado() + "', "
                    + "fecha_inicio_timbrado='" + timbrado.getFecha_inicio_timbrado() + "', "
                    + "fecha_vencimiento_timbrado='" + timbrado.getFecha_vencimiento_timbrado() + "', "
                    //+ "fecha_actual_timbrado='" + timbrado.getFecha_actual_timbrado() + "', "
                    + "desde_timbrado='" + timbrado.getDesde_timbrado() + "', "
                    + "hasta_timbrado='" + timbrado.getHasta_timbrado() + "', "
                    + "estado_timbrado='" + timbrado.getEstado_timbrado() + "', "
                    + "id_puesto='" + timbrado.getPuesto().getId_puesto() + "', "
                    + "id_establecimiento='" + timbrado.getEstablecimiento().getId_establecimiento() + "'"
                    + " where id_timbrado=" + timbrado.getId_timbrado();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from timbrados where id_timbrado=" + timbrado.getId_timbrado();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Timbrados buscarId(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados t, puestos p, establecimientos e where t.id_puesto = p.id_puesto and t.id_establecimiento = e.id_establecimiento and t.id_timbrado ='"+timbrado.getId_timbrado()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFecha_inicio_timbrado(rs.getDate("fecha_inicio_timbrado"));
                    timbrado.setFecha_vencimiento_timbrado(rs.getDate("fecha_vencimiento_timbrado"));
                    //timbrado.setFecha_actual_timbrado(rs.getDate("fecha_actual_timbrado"));
                    timbrado.setDesde_timbrado(rs.getInt("desde_timbrado"));
                    timbrado.setHasta_timbrado(rs.getInt("hasta_timbrado"));
                    timbrado.setEstado_timbrado(rs.getString("estado_timbrado"));
                    Puestos puesto = new Puestos();
                    puesto.setId_puesto(rs.getInt("id_puesto"));
                    puesto.setNombre_puesto(rs.getString("nombre_puesto"));
                    timbrado.setPuesto(puesto);
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));
                    timbrado.setEstablecimiento(establecimiento);
                } else {
                    timbrado.setId_timbrado(0);
                    timbrado.setNumero_timbrado("");
                    timbrado.setFecha_inicio_timbrado(null);
                    timbrado.setFecha_vencimiento_timbrado(null);
                    //timbrado.setFecha_actual_timbrado(null);
                    timbrado.setDesde_timbrado(0);
                    timbrado.setHasta_timbrado(0);
                    timbrado.setEstado_timbrado("");
                    Puestos puesto = new Puestos();
                    puesto.setId_puesto(0);
                    puesto.setNombre_puesto("");
                    timbrado.setPuesto(puesto);
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");
                    timbrado.setEstablecimiento(establecimiento);
                }   
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
    
    public static String buscarNumero(String numero, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(numero);
                String sql = "select * from timbrados where upper(numero_timbrado) like '%" +
                        numero.toUpperCase() + "%'"
                        + "order by id_timbrado offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_timbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
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
    
    public static Timbrados buscarTimbrado(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados where numero_timbrado ='"+timbrado.getNumero_timbrado()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setId_timbrado(0);
                    
                } else {
                    timbrado.setId_timbrado(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
}
