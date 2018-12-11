
<%@page import="controladores.MarcasControlador"%>
<%@page import="modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_marca = (request.getParameter("nombre_marca"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Marcas marca = new Marcas();
    marca.setNombre_marca(nombre_marca);
    
   MarcasControlador.buscarMarca(marca);
    if (marca.getId_marca()==0){
        tipo = "success";
        mensaje = "Marca repetida.";
        nuevo = "false";
    } /*else {
        marca = new Marcas();
    }*/
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
