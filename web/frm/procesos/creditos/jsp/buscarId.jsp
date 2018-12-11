
<%@page import="controladores.CreditosControlador"%>
<%@page import="modelos.Creditos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_aprobacioncredito = Integer.parseInt(request.getParameter("id_aprobacioncredito"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Creditos aprobacioncredito = new Creditos();
    aprobacioncredito.setId_aprobacioncredito(id_aprobacioncredito);
    
   CreditosControlador.buscarId(aprobacioncredito);
    if (aprobacioncredito.getId_aprobacioncredito()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_aprobacioncredito", aprobacioncredito.getId_aprobacioncredito());
    obj.put("fecha_aprobacioncredito", String.valueOf(aprobacioncredito.getFecha_aprobacioncredito()));
    obj.put("descripcion_aprobacioncredito", aprobacioncredito.getDescripcion_aprobacioncredito());
    obj.put("estado_aprobacioncredito", aprobacioncredito.getEstado_aprobacioncredito());
    obj.put("referencia_comercial", aprobacioncredito.getReferencia_comercial());
    obj.put("referencia_personal", aprobacioncredito.getReferencia_personal());
    obj.put("limite_credito", aprobacioncredito.getLimite_credito());
    out.print(obj);
    out.flush();
%>
