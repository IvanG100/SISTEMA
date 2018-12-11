<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste_stock_detalle = Integer.parseInt(request.getParameter("id_ajuste_stock_detalle"));
    int cantidad_ajuste_stock = Integer.parseInt(request.getParameter("cantidad_ajuste_stock"));
    int id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setId_ajuste_stock_detalle(id_ajuste_stock_detalle);
    detalleajuste.setCantidad_ajuste_stock(cantidad_ajuste_stock);
    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    detalleajuste.setAjuste(ajuste);
    detalleajuste.setArticulo(articulo);
    if (DetallesAjustesControlador.modificar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>