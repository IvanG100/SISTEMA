
<%@page import="controladores.Formas_pagosControlador"%>
<%@page import="modelos.Formas_pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_forma_pago = Integer.parseInt(request.getParameter("id_forma_pago"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Formas_pagos forma_pago = new Formas_pagos();
    forma_pago.setId_forma_pago(id_forma_pago);
    
   Formas_pagosControlador.buscarId(forma_pago);
    if (forma_pago.getId_forma_pago()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        forma_pago = new Formas_pagos();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_forma_pago", forma_pago.getId_forma_pago());
    obj.put("nombre_forma_pago", forma_pago.getNombre_forma_pago());
    out.print(obj);
    out.flush();
%>
