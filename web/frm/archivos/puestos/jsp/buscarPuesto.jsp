
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_puesto = (request.getParameter("nombre_puesto"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Puestos puesto = new Puestos();
    puesto.setNombre_puesto(nombre_puesto);
    
   PuestosControlador.buscarPuesto(puesto);
    if (puesto.getId_puesto()==0){
        tipo = "success";
        mensaje = "Puesto repetida.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
