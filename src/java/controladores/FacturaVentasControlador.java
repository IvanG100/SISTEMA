/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.math.BigDecimal;
import modelos.Clientes;
//import modelos.Tipo_facturas;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Establecimientos;
import modelos.FacturaVentas;
import modelos.Puestos;
import modelos.Timbrados;
import modelos.Tipo_facturas;

public class FacturaVentasControlador {

    public static FacturaVentas buscarId(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fv "
                        + "left join clientes c on fv.id_cliente=c.id_cliente "
                        + "left join tipo_facturas tf on fv.id_tipo_factura=tf.id_tipo_factura "
                        + "left join timbrados t on fv.id_timbrado=t.id_timbrado "
                        + "where estado_venta != 'COBRADO' and id_factura_venta=?";
                System.out.println("sql ::" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                        /*facturaventa.setSubtotal_5(rs.getInt("subtotal_5"));
                        facturaventa.setSubtotal_10(rs.getInt("subtotal_10"));
                        facturaventa.setSubtotal_exenta(rs.getInt("subtotal_exenta"));*/
                        facturaventa.setCantidad_cuotas(rs.getInt("cantidad_cuotas"));
                        facturaventa.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        facturaventa.setCliente(cliente);
                        facturaventa.setFecha_factura_venta(rs.getDate("fecha_factura_venta"));
                        Tipo_facturas tipofactura = new Tipo_facturas();
                        tipofactura.setId_tipo_factura(rs.getInt("id_tipo_factura"));
                        tipofactura.setNombre_tipo_factura(rs.getString("nombre_tipo_factura"));
                        facturaventa.setTipofactura(tipofactura);
                        
                        Puestos puesto = new Puestos();
                        puesto.setId_puesto(rs.getInt("id_puesto"));
                        //puesto.setNombre_puesto("");

                        Establecimientos establecimiento = new Establecimientos();
                        establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                        //establecimiento.setNombre_establecimiento("");

                        Timbrados timbrado = new Timbrados();
                        timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                        timbrado.setPuesto(puesto);
                        timbrado.setEstablecimiento(establecimiento);
                        facturaventa.setTimbrado(timbrado);

                    } else {
                        try {

                            String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura_venta) FROM factura_ventas ";

                            System.out.println("sss" + sqli);
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    facturaventa = new FacturaVentas();
                                    num = rsi.getInt("Ultimo");
                                    facturaventa.setNumero_factura_venta(num + 1);

                                    facturaventa.setId_factura_venta(0);
                                    //facturaventa.setEstado_venta("ACTIVO");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    facturaventa.setFecha_factura_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setId_cliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    facturaventa.setCliente(cliente);

                                    Tipo_facturas pago = new Tipo_facturas();
                                    pago.setId_tipo_factura(0);
                                    pago.setNombre_tipo_factura("");
                                    facturaventa.setTipofactura(pago);
                                    
                                    Puestos puesto = new Puestos();
                                    puesto.setId_puesto(0);
                                    //puesto.setNombre_puesto("");

                                    Establecimientos establecimiento = new Establecimientos();
                                    establecimiento.setId_establecimiento(0);
                                    //establecimiento.setNombre_establecimiento("");

                                    Timbrados timbrado = new Timbrados();
                                    timbrado.setId_timbrado(0);
                                    timbrado.setPuesto(puesto);
                                    timbrado.setEstablecimiento(establecimiento);
                                    facturaventa.setTimbrado(timbrado);
                                } else {
                                    num = 1;
                                    facturaventa = new FacturaVentas();
                                    
                                    facturaventa.setNumero_factura_venta(num);

                                    facturaventa.setId_factura_venta(0);
                                    //facturaventa.setEstado_venta("ACTIVO");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    facturaventa.setFecha_factura_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setId_cliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    facturaventa.setCliente(cliente);

                                    Tipo_facturas pago = new Tipo_facturas();
                                    pago.setId_tipo_factura(0);
                                    pago.setNombre_tipo_factura("");
                                    facturaventa.setTipofactura(pago);
                                    
                                    Puestos puesto = new Puestos();
                                    puesto.setId_puesto(0);
                                    //puesto.setNombre_puesto("");

                                    Establecimientos establecimiento = new Establecimientos();
                                    establecimiento.setId_establecimiento(0);
                                    //establecimiento.setNombre_establecimiento("");

                                    Timbrados timbrado = new Timbrados();
                                    timbrado.setId_timbrado(0);
                                    timbrado.setPuesto(puesto);
                                    timbrado.setEstablecimiento(establecimiento);
                                    facturaventa.setTimbrado(timbrado);
                                }

                                psi.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fv "
                        + "left join clientes c on c.id_cliente=fv.id_cliente "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "and fv.estado_venta!='COBRADO' order by id_factura_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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
    
    public static String buscarNombreContado(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fv "
                        + "left join clientes c on c.id_cliente=fv.id_cliente "
                        + "left join tipo_facturas tf on fv.id_tipo_factura=tf.id_tipo_factura "
                        + "where tf.nombre_tipo_factura = 'CONTADO' and upper(ci_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_factura_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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

    public static boolean agregar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getId_cliente();
            Date v2 = facturaventa.getFecha_factura_venta();
            int v3 = facturaventa.getTipofactura().getId_tipo_factura();
            /*int v4 = facturaventa.getSubtotal_5();
            int v5 = facturaventa.getSubtotal_10();
            int v6 = facturaventa.getSubtotal_exenta();*/
            int v4 = facturaventa.getCantidad_cuotas();
            //int v5 = facturaventa.getPedido().getId_pedido();
            int v5 = facturaventa.getNumero_factura_venta();
            int v7 = facturaventa.getTimbrado().getId_timbrado();
            int v8 = facturaventa.getUsuario().getId_usuario();
            String v6 = facturaventa.getEstado_venta();
            String sql = "insert into factura_ventas(id_cliente, fecha_factura_venta, id_tipo_factura, cantidad_cuotas, numero_factura_venta, estado_venta, id_timbrado, id_usuario) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "','" + v6 + "','" + v7 + "','" + v8 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura_venta = keyset.getInt(1);
                    facturaventa.setId_factura_venta(id_factura_venta);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getId_cliente();
            int v2 = facturaventa.getTipofactura().getId_tipo_factura();
            int v3 = facturaventa.getId_factura_venta();
            int v4 = facturaventa.getCantidad_cuotas();
            String sql = "update factura_ventas set id_cliente='" + v1 + "',"
                    + "id_tipo_factura='" + v2 + "',"
                    + "cantidad_cuotas='" + v4 + "' "
                    + "where id_factura_venta='" + v3 + "'";
            System.out.println("--> cerro " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                //Conexion.getConn().commit();
                System.out.println("--> Grabado y modificado");
                valor = true;
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    //System.out.println("--> " + ex1.getLocalizedMessage());
                    Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    /*public static boolean modificar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set id_cliente=?,"
                    + "id_tipo_factura=?,"
                    + "cantidad_cuotas=? "
                    + "where id_factura_venta=?";
            
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturaventa.getCliente().getId_cliente());
                ps.setInt(2, facturaventa.getTipofactura().getId_tipo_factura());
                ps.setInt(3, facturaventa.getCantidad_cuotas());
                ps.setInt(4, facturaventa.getId_factura_venta());
                ps.executeUpdate();
                System.out.println("--> cerro "+ sql);
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado y modificado");
                valor = true;
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    //System.out.println("--> " + ex1.getLocalizedMessage());
                    Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
    public static boolean Anular(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set estado_venta = 'ANULADO' where id_factura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturaventa.getId_factura_venta());
                ps.executeUpdate();
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

    /*public static boolean subtotaliva(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set subtotal_5=subtotal_5+?,"
                    + "subtotal_10=subtotal_10+?,"
                    + "subtotal_exenta=subtotal_exenta+? "
                    + "where id_factura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturaventa.getSubtotal_5());
                ps.setInt(2, facturaventa.getSubtotal_10());
                ps.setInt(3, facturaventa.getSubtotal_exenta());
                ps.setInt(4, facturaventa.getId_factura_venta());
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
    }*/
    /*public static boolean agregarFPedidos(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getId_cliente();
            Date v2 = facturaventa.getFecha_factura_venta();
            int v3 = facturaventa.getTipofactura().getId_tipo_factura();
            /*int v4 = facturaventa.getSubtotal_5();
            int v5 = facturaventa.getSubtotal_10();
            int v6 = facturaventa.getSubtotal_exenta();
            int v4 = facturaventa.getCantidad_cuotas();
            int v5 = facturaventa.getPedido().getId_pedido();
            String sql = "insert into factura_ventas(id_cliente, fecha_factura_venta, id_tipo_factura, cantidad_cuotas, id_pedido) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura_venta = keyset.getInt(1);
                    facturaventa.setId_factura_venta(id_factura_venta);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }*/

    public static FacturaVentas buscarTotalfactura(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select fdv.id_factura_venta, tf.nombre_tipo_factura, fv.estado_venta, sum(cantidad_venta * precio_venta) as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta \n"
                        + "left join tipo_facturas tf on fv.id_tipo_factura=tf.id_tipo_factura\n"
                        + "left join articulos a on a.id_articulo=fdv.id_articulo where fdv.id_factura_venta=? and fv.estado_venta= 'PENDIENTE' and tf.nombre_tipo_factura= 'CONTADO' group by fdv.id_factura_venta, tf.nombre_tipo_factura, fv.estado_venta";
                System.out.println("sql ::" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                        facturaventa.setTotal(rs.getInt("totalapagar"));
                        //facturaventa.setNumero_factura_venta(rs.getInt("numero_factura_venta"));

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }
    
    public static FacturaVentas buscarTotalf(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select fdv.id_factura_venta, tf.nombre_tipo_factura, fv.estado_venta, sum(cantidad_venta * precio_venta) as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta \n"
                        + "left join tipo_facturas tf on fv.id_tipo_factura=tf.id_tipo_factura\n"
                        + "left join articulos a on a.id_articulo=fdv.id_articulo where fdv.id_factura_venta=? and fv.estado_venta= 'PENDIENTE' and tf.nombre_tipo_factura= 'CREDITO' group by fdv.id_factura_venta, tf.nombre_tipo_factura, fv.estado_venta";
                System.out.println("sql ::" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                        facturaventa.setTotal(rs.getInt("totalapagar"));
                        //facturaventa.setNumero_factura_venta(rs.getInt("numero_factura_venta"));

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }

    public static boolean modificarestadocobro(FacturaVentas facturaventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from factura_ventas fv, tipo_facturas tf where fv.id_tipo_factura=tf.id_tipo_factura and tf.nombre_tipo_factura= 'CONTADO' and estado_venta = 'PENDIENTE'";
            System.out.println("idfv-> " + sql);
            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String sql2 = "update factura_ventas set estado_venta='COBRADO'  "
                                + " where id_factura_venta=" + facturaventa.getId_factura_venta();
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
                    } else {
                        String sql3 = "select * from cuentas c, factura_ventas fv where c.id_factura_venta = fv.id_factura_venta and c.estado_cuenta= 'ACTIVO'";
                        try {
                            try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql3)) {
                                ResultSet rs1 = ps1.executeQuery();
                                if (!rs1.next()) {
                                    String sql4 = "update factura_ventas set estado_venta='COBRADO'  "
                                            + " where id_factura_venta=" + facturaventa.getId_factura_venta();
                                    System.out.println("idfv-> " + sql4);
                                    try {

                                        Conexion.getSt().executeUpdate(sql4);

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
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    /*public static boolean modificarestadocobro(FacturaVentas facturaventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from cuentas c, factura_ventas fv where c.id_factura_venta = fv.id_factura_venta and c.estado_cuenta= 'ACTIVO'";
            System.out.println("idfv-> " + sql);
            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        String sql2 = "update factura_ventas set estado_venta='COBRADO'  "
                                + " where id_factura_venta=" + facturaventa.getId_factura_venta();
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
                    } else {
                        String sql3 = "select * from factura_ventas fv, tipo_facturas tf where fv.id_tipo_factura=tf.id_tipo_factura and tf.nombre_tipo_factura= 'CONTADO' and estado_venta = 'PENDIENTE'";
                        try {
                            try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql3)) {
                                ResultSet rs1 = ps1.executeQuery();
                                if (rs1.next()) {
                                    String sql4 = "update factura_ventas set estado_venta='COBRADO'  "
                                            + " where id_factura_venta=" + facturaventa.getId_factura_venta();
                                    System.out.println("idfv-> " + sql4);
                                    try {

                                        Conexion.getSt().executeUpdate(sql4);

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
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
}
