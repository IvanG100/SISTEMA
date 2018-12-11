
<%@page import="controladores.SucursalesControlador"%>
<%@page import="modelos.Sucursales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Sucursales sucursal = new Sucursales();
    sucursal.setId_sucursal(id_sucursal);
    
    if (SucursalesControlador.eliminar(sucursal)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>