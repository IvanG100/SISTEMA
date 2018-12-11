
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    
    if (TimbradosControlador.eliminar(timbrado)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>