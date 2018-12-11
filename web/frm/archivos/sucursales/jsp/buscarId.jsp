
<%@page import="controladores.SucursalesControlador"%>
<%@page import="modelos.Sucursales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Sucursales sucursal = new Sucursales();
    sucursal.setId_sucursal(id_sucursal);
    
   SucursalesControlador.buscarId(sucursal);
    if (sucursal.getId_sucursal()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_sucursal", sucursal.getId_sucursal());
    obj.put("nombre_sucursal", sucursal.getNombre_sucursal());
    out.print(obj);
    out.flush();
%>
