
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String numero_timbrado = (request.getParameter("numero_timbrado"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setNumero_timbrado(numero_timbrado);
    
   TimbradosControlador.buscarTimbrado(timbrado);
    if (timbrado.getId_timbrado()==0){
        tipo = "success";
        mensaje = "Timbrado repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
