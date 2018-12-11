
<%@page import="controladores.Tipo_facturasControlador"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_tipo_factura = (request.getParameter("nombre_tipo_factura"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Tipo_facturas tipo_factura = new Tipo_facturas();
    tipo_factura.setNombre_tipo_factura(nombre_tipo_factura);
    
   Tipo_facturasControlador.buscarTipoFactura(tipo_factura);
    if (tipo_factura.getId_tipo_factura()==0){
        tipo = "success";
        mensaje = "Tipo de factura repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
