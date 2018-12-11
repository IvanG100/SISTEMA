
<%@page import="controladores.CreditosControlador"%>
<%@page import="modelos.Creditos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_aprobacioncredito = Integer.parseInt(request.getParameter("id_aprobacioncredito"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Creditos aprobacioncredito = new Creditos();
    aprobacioncredito.setId_aprobacioncredito(id_aprobacioncredito);
    
    if (CreditosControlador.eliminar(aprobacioncredito)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>