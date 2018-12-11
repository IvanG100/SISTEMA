
<%@page import="controladores.SucursalesControlador"%>
<%@page import="modelos.Sucursales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));
    String nombre_sucursal = request.getParameter("nombre_sucursal");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Sucursales sucursal = new Sucursales();
    sucursal.setId_sucursal(id_sucursal);
    sucursal.setNombre_sucursal(nombre_sucursal);
    
    if (SucursalesControlador.agregar(sucursal)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

