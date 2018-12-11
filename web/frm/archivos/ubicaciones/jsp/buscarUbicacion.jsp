
<%@page import="controladores.UbicacionesControlador"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_ubicacion = (request.getParameter("nombre_ubicacion"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setNombre_ubicacion(nombre_ubicacion);
    
   UbicacionesControlador.buscarUbicacion(ubicacion);
    if (ubicacion.getId_ubicacion()==0){
        tipo = "success";
        mensaje = "Ubicacion repetida.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
