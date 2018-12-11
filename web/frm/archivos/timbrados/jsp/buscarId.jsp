<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    
   TimbradosControlador.buscarId(timbrado);
    if (timbrado.getId_timbrado()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_timbrado", timbrado.getId_timbrado());
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());
    obj.put("fecha_inicio_timbrado", String.valueOf(timbrado.getFecha_inicio_timbrado()));
    obj.put("fecha_vencimiento_timbrado", String.valueOf(timbrado.getFecha_vencimiento_timbrado()));
    //obj.put("fecha_actual_timbrado", String.valueOf(timbrado.getFecha_actual_timbrado()));
    obj.put("desde_timbrado", timbrado.getDesde_timbrado());
    obj.put("hasta_timbrado", timbrado.getHasta_timbrado());
    obj.put("estado_timbrado", timbrado.getEstado_timbrado());
    obj.put("id_puesto", timbrado.getPuesto().getId_puesto());
    obj.put("nombre_puesto", timbrado.getPuesto().getNombre_puesto());
    obj.put("id_establecimiento", timbrado.getEstablecimiento().getId_establecimiento());
    obj.put("nombre_establecimiento", timbrado.getEstablecimiento().getNombre_establecimiento());
    out.print(obj);
    out.flush();
%>
