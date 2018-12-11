
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_categoria = (request.getParameter("nombre_categoria"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Categorias categoria = new Categorias();
    categoria.setNombre_categoria(nombre_categoria);
    
   CategoriasControlador.buscarCategoria(categoria);
    if (categoria.getId_categoria()==0){
        tipo = "success";
        mensaje = "Categoria repetida.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
