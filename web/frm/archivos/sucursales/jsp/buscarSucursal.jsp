
<%@page import="controladores.SucursalesControlador"%>
<%@page import="modelos.Sucursales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_sucursal = (request.getParameter("nombre_sucursal"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Sucursales sucursal = new Sucursales();
    sucursal.setNombre_sucursal(nombre_sucursal);
    
   SucursalesControlador.buscarSucursal(sucursal);
    if (sucursal.getId_sucursal()==0){
        tipo = "success";
        mensaje = "Sucursal repetida.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
