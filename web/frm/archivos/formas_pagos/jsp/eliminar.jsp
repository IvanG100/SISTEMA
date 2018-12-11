
<%@page import="controladores.Formas_pagosControlador"%>
<%@page import="modelos.Formas_pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_forma_pago = Integer.parseInt(request.getParameter("id_forma_pago"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Formas_pagos forma_pago = new Formas_pagos();
    forma_pago.setId_forma_pago(id_forma_pago);
    
    if (Formas_pagosControlador.eliminar(forma_pago)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>