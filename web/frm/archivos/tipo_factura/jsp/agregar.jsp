
<%@page import="controladores.Tipo_facturasControlador"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    String nombre_tipo_factura = request.getParameter("nombre_tipo_factura");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Tipo_facturas tipo_factura = new Tipo_facturas();
    tipo_factura.setId_tipo_factura(id_tipo_factura);
    tipo_factura.setNombre_tipo_factura(nombre_tipo_factura);
    
    if (Tipo_facturasControlador.agregar(tipo_factura)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

