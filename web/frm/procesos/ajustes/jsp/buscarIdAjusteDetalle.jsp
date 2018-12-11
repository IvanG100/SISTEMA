<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste_stock_detalle = Integer.parseInt(request.getParameter("id_ajuste_stock_detalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesAjustes detalleajuste = DetallesAjustesControlador.buscarId(id_ajuste_stock_detalle);
    if (detalleajuste != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleajuste = new DetallesAjustes();
        detalleajuste.setId_ajuste_stock_detalle(0);
        detalleajuste.setCantidad_ajuste_stock(0);
        
        Ajustes ajuste = new Ajustes();
        ajuste.setId_ajuste_stock(0);
        detalleajuste.setAjuste(ajuste);
        
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_articulo("");
        articulo.setPrecio_compra(0);
        detalleajuste.setArticulo(articulo);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_ajuste_stock_detalle", detalleajuste.getId_ajuste_stock_detalle());
    obj.put("id_ajuste_stock", detalleajuste.getAjuste().getId_ajuste_stock());
    obj.put("id_articulo", detalleajuste.getArticulo().getId_articulo());
    obj.put("nombre_articulo", detalleajuste.getArticulo().getNombre_articulo());
    obj.put("precio_compra", detalleajuste.getArticulo().getPrecio_compra());
    obj.put("cantidad_ajuste_stock", detalleajuste.getCantidad_ajuste_stock());    
    out.print(obj);
    out.flush();
%>