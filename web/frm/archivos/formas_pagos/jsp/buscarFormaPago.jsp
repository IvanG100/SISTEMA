
<%@page import="controladores.Formas_pagosControlador"%>
<%@page import="modelos.Formas_pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_forma_pago = (request.getParameter("nombre_forma_pago"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Formas_pagos forma_pago = new Formas_pagos();
    forma_pago.setNombre_forma_pago(nombre_forma_pago);
    
   Formas_pagosControlador.buscarFormaPago(forma_pago);
    if (forma_pago.getId_forma_pago()==0){
        tipo = "success";
        mensaje = "Forma de pago repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
