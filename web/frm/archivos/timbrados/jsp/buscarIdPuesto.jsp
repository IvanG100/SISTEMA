
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    
   PuestosControlador.buscarId(puesto);
    if (puesto.getId_puesto()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_puesto", puesto.getId_puesto());
    obj.put("nombre_puesto", puesto.getNombre_puesto());
    obj.put("id_establecimiento", puesto.getEstablecimiento().getId_establecimiento());
    obj.put("nombre_establecimiento", puesto.getEstablecimiento().getNombre_establecimiento());
    out.print(obj);
    out.flush();
%>
