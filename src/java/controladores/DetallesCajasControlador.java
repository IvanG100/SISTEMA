package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Cajas;
import modelos.DetallesCajas;
import modelos.Formas_pagos;
import modelos.FacturaVentas;
import utiles.Conexion;
import utiles.Utiles;

//import javawebts.modelos.Ventas;
//import javawebts.modelos.DetallesCajas;
//import javawebts.modelos.Cajas;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesCajasControlador {

    public static DetallesCajas buscarId(int id) throws SQLException {
        DetallesCajas detallecaja = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join factura_ventas a on a.id_factura_venta=dp.id_factura_venta "
                        + "left join formas_pagos t on t.id_forma_pago=dp.id_forma_pago "
                        + "where dp.id_detallecaja=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("detallecj--> " + sql+id);
                    if (rs.next()) {
                        
                        detallecaja = new DetallesCajas();
                        detallecaja.setId_detallecaja(rs.getInt("id_detallecaja"));
                        detallecaja.setImporte(rs.getInt("importe"));
                        //detallecaja.setVuelto(rs.getInt("vuelto"));

                        FacturaVentas venta = new FacturaVentas();
                        venta.setId_factura_venta(rs.getInt("id_factura_venta"));
                        //venta.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                        detallecaja.setFacturaventa(venta);

                        Cajas caja = new Cajas();
                        caja.setId_caja(rs.getInt("id_caja"));
                        detallecaja.setCaja(caja);

                        Formas_pagos pago = new Formas_pagos();
                        pago.setId_forma_pago(rs.getInt("id_forma_pago"));
                        //pago.setNombre_forma_pago(rs.getString("nombre_forma_pago"));
                        detallecaja.setPago(pago);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join factura_ventas v on v.id_factura_venta=dp.id_factura_venta "
                        + "left join formas_pagos a on a.id_forma_pago=dp.id_forma_pago "
                        + "where dp.id_caja=" + id + " "
                        + "order by id_detallecaja";
                System.out.println("dca--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("importe");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                              //  + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                //+ "<td>" + rs.getString("id_forma_pago") + "</td>"
                                + "<td>" + rs.getString("nombre_forma_pago") + "</td>"
                               // + "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                /*+ "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecaja") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"*/
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join factura_ventas a on a.id_factura_venta=dp.id_factura_venta "
                        + "where upper(a.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                + "<td>" + rs.getInt("total") + "</td>"
                                + "<td>" + rs.getInt("iva_venta") + "</td>"
                                + "<td>" + rs.getInt("importe") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescajas "
                    + "(id_factura_venta,id_forma_pago,id_caja,importe) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
                ps.setInt(1, detallecaja.getFacturaventa().getId_factura_venta());
                ps.setInt(2, detallecaja.getPago().getId_forma_pago());
                ps.setInt(3, detallecaja.getCaja().getId_caja());
                ps.setInt(4, detallecaja.getImporte());
                //ps.setInt(5, detallecaja.getVuelto());

                ps.executeUpdate();
                System.out.println("agragercajadetalle "+sql);
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescajas set "
                    + "id_caja=?,"
                    + "id_factura_venta=?,"
                    + "cantidad_ventacaja=? "
                    + "where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());
                ps.setInt(2, detallecaja.getFacturaventa().getId_factura_venta());
                //  ps.setInt(3, detallecaja.getCantidad_ventacaja());
                ps.setInt(4, detallecaja.getId_detallecaja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getId_detallecaja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarc(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join ventas a on a.id_factura_venta=dp.id_factura_venta "
                        + " where p.id_caja= " + detallecaja.getCaja().getId_caja();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_exi= cantidad_exi - " + rs.getInt("cantidad_ventacaja") + " where id_factura_venta=" + rs.getInt("id_factura_venta") + "";

                        System.out.println(" descontar stcok" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
                            Conexion.getConn().setAutoCommit(false);
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarVentaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + caja.getId_caja());
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
        Conexion.cerrar();
        return valor;
    }
}
