
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String codigo_articulo = (request.getParameter("codigo_articulo"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Articulos articulo = new Articulos();
    articulo.setCodigo_articulo(codigo_articulo);
    
   ArticulosControlador.buscarCodigo(articulo);
    if (articulo.getId_articulo() == 0){
        tipo = "success";
        mensaje = "Codigo repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    
    out.print(obj);
    out.flush();
%>
