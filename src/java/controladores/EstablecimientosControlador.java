/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudades;

import modelos.Establecimientos;

import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class EstablecimientosControlador {


    public static boolean agregar(Establecimientos establecimiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into establecimientos(nombre_establecimiento,actividad_economica,ruc_establecimiento,representante_establecimiento,telefono_establecimiento,direccion_establecimiento,id_ciudad) "
                    + "values('" + establecimiento.getNombre_establecimiento() + "','"
                    + establecimiento.getActividad_economica()+ "','"
                    + establecimiento.getRuc_establecimiento()+ "','"
                    + establecimiento.getRepresentante_establecimiento()+ "','"
                    + establecimiento.getTelefono_establecimiento() + "','"
                    + establecimiento.getDireccion_establecimiento() + "','"
                    + establecimiento.getCiudad().getId_ciudad() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Establecimientos buscarId(Establecimientos establecimiento) {
        if (Conexion.conectar()) {
            String sql = "select * from establecimientos a, ciudades c "
                    + " where a.id_ciudad=c.id_ciudad and "
                    + "id_establecimiento='" + establecimiento.getId_establecimiento() + "'";

            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));
                    establecimiento.setActividad_economica(rs.getString("actividad_economica"));
                    establecimiento.setRuc_establecimiento(rs.getString("ruc_establecimiento"));
                    establecimiento.setRepresentante_establecimiento(rs.getString("representante_establecimiento"));
                    establecimiento.setTelefono_establecimiento(rs.getString("telefono_establecimiento"));
                    establecimiento.setDireccion_establecimiento(rs.getString("direccion_establecimiento"));
                    

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));

                    establecimiento.setCiudad(ciudad);

                } else {
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");
                    establecimiento.setActividad_economica("");
                    establecimiento.setRuc_establecimiento("");
                    establecimiento.setRepresentante_establecimiento("");
                    establecimiento.setTelefono_establecimiento("");
                    establecimiento.setDireccion_establecimiento("");
                    
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");

                    establecimiento.setCiudad(ciudad);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from establecimientos where upper(nombre_establecimiento) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_establecimiento offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_establecimiento") + "</td>"
                                + "<td>" + rs.getString("nombre_establecimiento") + "</td>"
                                + "<td>" + rs.getString("actividad_economica") + "</td>"
                                + "<td>" + rs.getString("ruc_establecimiento") + "</td>"
                                + "<td>" + rs.getString("representante_establecimiento") + "</td>"
                                + "<td>" + rs.getString("telefono_establecimiento") + "</td>"
                                + "<td>" + rs.getString("direccion_establecimiento") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
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

    public static boolean modificar(Establecimientos establecimiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update establecimientos set nombre_establecimiento='" + establecimiento.getNombre_establecimiento()
                    + "',actividad_economica='" + establecimiento.getActividad_economica() + ""
                    + "',ruc_establecimiento='" + establecimiento.getRuc_establecimiento()+ ""
                    + "',representante_establecimiento='" + establecimiento.getRepresentante_establecimiento()+ ""
                    + "',telefono_establecimiento='" + establecimiento.getTelefono_establecimiento()+ ""
                    + "',direccion_establecimiento='" + establecimiento.getDireccion_establecimiento()+ ""
                    + "',id_ciudad='" + establecimiento.getCiudad().getId_ciudad() + ""
                    + "' where id_establecimiento=" + establecimiento.getId_establecimiento();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Establecimientos establecimiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from establecimientos where id_establecimiento=" + establecimiento.getId_establecimiento();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

}
