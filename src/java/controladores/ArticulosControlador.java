/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Articulos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Categorias;
import modelos.Marcas;
import modelos.Ubicaciones;


public class ArticulosControlador {
    public static boolean agregar(Articulos articulo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into articulos (nombre_articulo, id_marca, id_categoria, descripcion_articulo, precio_venta, precio_compra, codigo_articulo, iva_articulo, id_ubicacion)" 
                    + "values ('" + articulo.getNombre_articulo() + "','"
                    + articulo.getMarca().getId_marca() + "','"
                    + articulo.getCategoria().getId_categoria() + "','"
                    + articulo.getDescripcion_articulo() + "','"
                    //+ articulo.getPrecio_unitario() + "','"
                    + articulo.getPrecio_venta() + "','"
                    + articulo.getPrecio_compra() + "','"
                    + articulo.getCodigo_articulo()+ "','"
                    + articulo.getIva_articulo()+ "','"
                    + articulo.getUbicacion().getId_ubicacion() + "')";
                    System.out.println("sql " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset=Conexion.getSt().getGeneratedKeys();
                if(keyset.next()){
                    int id_articulo=keyset.getInt(1);
                    articulo.setId_articulo(id_articulo);
                                      
                }
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Articulos articulo){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update articulos set nombre_articulo='" + articulo.getNombre_articulo()+ "', "
                    + "id_marca='" + articulo.getMarca().getId_marca() + "', "
                    + "id_categoria='" + articulo.getCategoria().getId_categoria() + "', "
                    + "descripcion_articulo='" + articulo.getDescripcion_articulo() + "', "
                    //+ "precio_unitario='" + articulo.getPrecio_unitario() + "', "
                    + "precio_venta='" + articulo.getPrecio_venta() + "', "
                    + "precio_compra='" + articulo.getPrecio_compra() + "', "
                    + "codigo_articulo='" + articulo.getCodigo_articulo() + "', "
                    + "iva_articulo='" + articulo.getIva_articulo() + "', "
                    + "id_ubicacion='" + articulo.getUbicacion().getId_ubicacion() + "'"
                    + " where id_articulo=" + articulo.getId_articulo();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Articulos articulo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from articulos where id_articulo=" + articulo.getId_articulo();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Articulos buscarId(Articulos articulo) {
        if (Conexion.conectar()){
            String sql = "select * from articulos a, marcas m, categorias c, ubicaciones u where a.id_marca = m.id_marca and a.id_categoria = c.id_categoria and a.id_ubicacion = u.id_ubicacion and a.id_articulo ='"+articulo.getId_articulo()+"'";
            System.out.println("sql "+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    articulo.setId_articulo(rs.getInt("id_articulo"));
                    articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    articulo.setMarca(marca);
                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                    articulo.setCategoria(categoria);
                    //articulo.setPrecio_unitario(rs.getInt("precio_unitario"));
                    articulo.setPrecio_venta(rs.getInt("precio_venta"));
                    articulo.setPrecio_compra(rs.getInt("precio_compra"));
                    articulo.setDescripcion_articulo(rs.getString("descripcion_articulo"));
                    //articulo.setIva_5(rs.getString("iva_5"));
                    articulo.setCodigo_articulo(rs.getString("codigo_articulo"));
                    
                    articulo.setIva_articulo(rs.getInt("iva_articulo"));
                    Ubicaciones ubicacion = new Ubicaciones();
                    ubicacion.setId_ubicacion(rs.getInt("id_ubicacion"));
                    ubicacion.setNombre_ubicacion(rs.getString("nombre_ubicacion"));
                    articulo.setUbicacion(ubicacion);
                    
                } else {
                    articulo.setId_articulo(0);
                    articulo.setNombre_articulo("");
                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");
                    articulo.setMarca(marca);
                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(0);
                    categoria.setNombre_categoria("");
                    articulo.setCategoria(categoria);
                    articulo.setDescripcion_articulo("");
                    //articulo.setPrecio_unitario(0);
                    articulo.setPrecio_venta(0);
                    articulo.setPrecio_compra(0);
                    //articulo.setIva_5("");
                    
                    articulo.setCodigo_articulo("");
                    
                    articulo.setIva_articulo(0);
                    Ubicaciones ubicacion = new Ubicaciones();
                    ubicacion.setId_ubicacion(0);
                    ubicacion.setNombre_ubicacion("");
                    articulo.setUbicacion(ubicacion);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return articulo;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from articulos where upper(nombre_articulo) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_articulo offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
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
    
    public static String buscarNombre1(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from articulos a, stock t where a.id_articulo = t.id_articulo and upper(nombre_articulo) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by a.id_articulo offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td>" + rs.getString("cantidad_stock") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=3> No existen registros...</td></tr>";
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
    
    public static Articulos buscarCodigo(Articulos articulo) {
        if (Conexion.conectar()){
            String sql = "select * from articulos where codigo_articulo ='"+articulo.getCodigo_articulo()+"'";
            System.out.println("sql "+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    articulo.setId_articulo(0);
                    
                } else {
                    articulo.setId_articulo(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return articulo;
    }
}
