/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import java.math.BigDecimal;
import modelos.CuentasClientes;
import modelos.FacturaVentas;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CuentasClientesControlador {
    public static boolean agregar(CuentasClientes cuentasclientes){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql2 = "insert into cuentas (id_factura_venta, monto_total, estado_cuenta, total_cuota)" 
                    + "values ('" + cuentasclientes.getFacturaventa().getId_factura_venta() + "','"
                    + cuentasclientes.getMonto_total() + "','"
                    //+ cuentasclientes.getNro_cuota() + "','"
                    //+ cuentasclientes.getVencimiento() + "','"
                    //+ cuentasclientes.getMonto_cuota() + "','"
                    + cuentasclientes.getEstado_cuenta() + "','"
                    + cuentasclientes.getTotal_cuota() + "')"; 
                    
            try {
                Conexion.getSt().executeUpdate(sql2);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CuentasClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean agregarcuenta(CuentasClientes cuentasclientes){
        boolean valor = false;
        if (Conexion.conectar()){
            try {
                String sql = "select fdv.id_factura_venta, fecha_factura_venta, sum(cantidad_venta * precio_venta)\n" +
"as TotalaPagar from factura_detalle_ventas fdv\n" +
"left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta\n" +
"left join articulos a on a.id_articulo=fdv.id_articulo where fdv.id_factura_venta='" + cuentasclientes.getFacturaventa().getId_factura_venta() +"' group by fdv.id_factura_venta, fecha_factura_venta";
                System.out.println("sql ::"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //BigDecimal total = BigDecimal.ZERO;
                    
                    while (rs.next()) {
                        
                        //vencimiento.add(Calendar.DATE, 30);
                        int totalmonto = rs.getInt("TotalaPagar");
                        int totalcuota = cuentasclientes.getTotal_cuota();
                        
                        int factura = rs.getInt("id_factura_venta");
                        
                        
                       
                        
                        String sql2 = "insert into cuentas (id_factura_venta, monto_total, estado_cuenta, total_cuota)" 
                    + "values (?,?,?,?)"; 

                        //if (rs.getInt("matricula_cursohabilitado") > 0) {
                        
                
                            try (PreparedStatement ps2 = Conexion.getConn().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                                
                                ps2.setInt(1, factura);
                                ps2.setInt(2, totalmonto);
                               
                                ps2.setString(3, cuentasclientes.getEstado_cuenta());
                                ps2.setInt(4, totalcuota);
                                
                                
                                ps2.executeUpdate();
                                ResultSet keyset=ps2.getGeneratedKeys();
                if(keyset.next()){
                    int id_cuenta=keyset.getInt(1);
                    cuentasclientes.setId_cuenta(id_cuenta);
                             System.out.println("carajo++ " + id_cuenta);         
                }
                                ps2.close();
                                //Conexion.getConn().setAutoCommit(false);
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }

                        //}

                        ///condicion si es que tiene cuota definida
                        
                    }
                    ps.close();
                    System.out.println("--> " + valor);
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }    
        }
        
        return valor;
        
    }
    
    public static boolean agregarcuenta1(CuentasClientes cuentasclientes){
        boolean valor = false;
        if (Conexion.conectar()){
            try {
                String sql = "select fdv.id_factura_venta, fecha_factura_venta, sum(cantidad_venta * precio_venta)\n" +
"as TotalaPagar from factura_detalle_ventas fdv\n" +
"left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta\n" +
"left join articulos a on a.id_articulo=fdv.id_articulo where fdv.id_factura_venta='" + cuentasclientes.getFacturaventa().getId_factura_venta() +"' group by fdv.id_factura_venta, fecha_factura_venta";
                System.out.println("sql ::"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //BigDecimal total = BigDecimal.ZERO;
                    int nro_cuota = 0;
                    String vence = "";
                    while (rs.next()) {
                        Calendar vencimiento = GregorianCalendar.getInstance();
                        vencimiento.setTime(rs.getDate("fecha_factura_venta"));
                        System.out.println("FECHA"+ rs.getDate("fecha_factura_venta"));
                        //vencimiento.add(Calendar.DATE, 30);
                        int totalcuota = rs.getInt("TotalaPagar");
                        int nrocuota = cuentasclientes.getTotal_cuota();
                        
                        int monto_cuota = totalcuota / nrocuota;
                        System.out.println("nro " + nrocuota + ", monto " + monto_cuota);
                        
                        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        

                        //if (rs.getInt("matricula_cursohabilitado") > 0) {
                            /*try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                psi.setInt(1, rs.getInt("id_factura_venta"));
                                psi.setInt(2, 1);
                                psi.setInt(3, 1);
                                psi.setInt(4, 1);
                                psi.setInt(5, rs.getInt("matricula_cursohabilitado"));
                                psi.setDate(6, rs.getDate("fecha_inscripcion"));
                                psi.executeUpdate();
                                psi.close();
                                Conexion.getConn().setAutoCommit(false);
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }*/

                        //}

                        ///condicion si es que tiene cuota definida
                        for (int i = 0; i < nrocuota; i++) {
                            nro_cuota = nro_cuota + 1;
                          //  nrocuota = nrocuota+1;
                               //System.out.println("nro " + nrocuota + ", cuota " + nro_cuota +" "+ cuotavence);
                               int factura = rs.getInt("id_factura_venta");
                            vence = sdf.format(vencimiento.getTime());
                            java.sql.Date cuotavence = Utiles.stringToSqlDate(vence);
                            System.out.println("nro " + nrocuota + ", cuota " + nro_cuota +" "+ cuotavence + "f "+ factura);
                            //cuotas 
                            String sqli = "insert into cuentas (id_factura_venta, monto_total, nro_cuota, vencimiento, monto_cuota, estado_cuenta, total_cuota)"
                                
                                + "values (?,?,?,?,?,?,?)";
                            System.out.println("sqlcuenta "+ sqli);
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                psi.setInt(1, factura);
                                psi.setInt(2, totalcuota);
                                psi.setInt(3, nro_cuota);
                                psi.setDate(4, cuotavence);
                                psi.setInt(5, monto_cuota);
                                psi.setString(6, cuentasclientes.getEstado_cuenta());
                                psi.setInt(7, nrocuota);
                                psi.executeUpdate();
                                psi.close();
                                Conexion.getConn().commit();
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }
                            vencimiento.add(Calendar.DATE, 30);
                        }
                    }
                    ps.close();
                    System.out.println("--> " + valor);
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }    
        }
        
        return valor;
        
    }
    
    public static boolean modificar(CuentasClientes cuentasclientes){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "select * from cuentas c, detallescuentas dc  where c.id_cuenta = dc.id_cuenta and dc.estado_cuota= 'PENDIENTE' and dc.id_cuenta =" + cuentasclientes.getId_cuenta();
                        try {
                            try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql)) {
                                ResultSet rs1 = ps1.executeQuery();
                                if (!rs1.next()) {
                                    String sql2 = "update cuentas set estado_cuenta='COBRADO'" 
                                    + " where id_cuenta=" + cuentasclientes.getId_cuenta();
                                    System.out.println("idfv-> " + sql2);
                                    try {

                                        Conexion.getSt().executeUpdate(sql2);

                                        Conexion.getConn().setAutoCommit(false);
                                        System.out.println("--> Grabado");
                                        valor = true;
                                    } catch (SQLException ex) {
                                        System.out.println("--> " + ex.getLocalizedMessage());
                                        try {
                                            Conexion.getConn().rollback();
                                        } catch (SQLException ex1) {
                                            System.out.println("--> " + ex1.getLocalizedMessage());
                                        }
                                    }
                                }
                                ps1.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("Error: " + ex);
                        }
            
            
            
            
                    
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(CuentasClientes cuentasclientes){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from ciudades where id_ciudad=" + cuentasclientes.getId_cuenta();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CuentasClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static CuentasClientes buscarId(CuentasClientes cuentasclientes) {
        if (Conexion.conectar()){
            String sql = "select * from cuentas where id_cuenta ='"+cuentasclientes.getId_cuenta()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cuentasclientes.setId_cuenta(rs.getInt("id_cuenta"));
                    FacturaVentas facturaventa = new FacturaVentas();
                    facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                    cuentasclientes.setFacturaventa(facturaventa);
                    cuentasclientes.setMonto_total(rs.getInt("monto_total"));
                    cuentasclientes.setNro_cuota(rs.getInt("nro_cuota"));
                    cuentasclientes.setVencimiento(rs.getDate("vencimiento"));
                    cuentasclientes.setMonto_cuota(rs.getInt("monto_cuota"));
                    cuentasclientes.setEstado_cuenta(rs.getString("estado_cuenta"));
                } 
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cuentasclientes;
    }
    
    public static CuentasClientes buscarId1(CuentasClientes cuentasclientes) {
        if (Conexion.conectar()){
            String sql = "select * from cuentas c, detallescuentas dc where c.id_cuenta=dc.id_cuenta and dc.estado_cuota='PENDIENTE' and dc.id_cuenta="+cuentasclientes.getId_cuenta() + " order by dc.nro_cuota";
                /*String sql = "select * from cuentas where upper(estado_cuenta) like '%" +
                        nombre.toUpperCase() + "%'";*/
                        
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cuentasclientes.setId_cuenta(rs.getInt("id_cuenta"));
                    FacturaVentas facturaventa = new FacturaVentas();
                    facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                    cuentasclientes.setFacturaventa(facturaventa);
                    cuentasclientes.setMonto_total(rs.getInt("monto_total"));
                    cuentasclientes.setNro_cuota(rs.getInt("nro_cuota"));
                    //cuentasclientes.setVencimiento(rs.getDate("vencimiento_cuota"));
                    cuentasclientes.setMonto_cuota(rs.getInt("monto_cuota"));
                    cuentasclientes.setEstado_cuenta(rs.getString("estado_cuenta"));
                } else {
                    cuentasclientes.setId_cuenta(0);
                    FacturaVentas facturaventa = new FacturaVentas();
                    facturaventa.setId_factura_venta(0);
                    cuentasclientes.setFacturaventa(facturaventa);
                    cuentasclientes.setMonto_total(0);
                   // cuentasclientes.setNro_cuota(0);
                    //cuentasclientes.setVencimiento(null);
                    cuentasclientes.setMonto_cuota(0);
                    cuentasclientes.setEstado_cuenta("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cuentasclientes;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                  String sql = "select * from cuentas ct, detallescuentas dc, factura_ventas f, clientes c, tipo_facturas tf where ct.id_cuenta=dc.id_cuenta and f.id_factura_venta=ct.id_factura_venta and tf.nombre_tipo_factura= 'CREDITO' and estado_cuota='PENDIENTE' and f.id_cliente=c.id_cliente and ci_cliente like '%"+nombre+"%'"
                /*String sql = "select * from cuentas where upper(estado_cuenta) like '%" +
                        nombre.toUpperCase() + "%'";*/
                        + " order by dc.nro_cuota offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cuenta") + "</td>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("monto_cuota") + "</td>"
                                + "<td>" + rs.getString("vencimiento_cuota") + "</td>"
                                //+ "<td>" + rs.getString("monto_total") + "</td>"
                                + "<td>" + rs.getString("estado_cuota") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=4> No existen registros...</td></tr>";
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
    
    
    
    
    /*public static Ciudades buscarCiudad(Ciudades ciudad) {
        if (Conexion.conectar()){
            String sql = "select * from ciudades where nombre_ciudad ='"+ciudad.getNombre_ciudad()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    ciudad.setId_ciudad(0);
                    
                } else {
                    ciudad.setId_ciudad(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return ciudad;
    }*/
}
