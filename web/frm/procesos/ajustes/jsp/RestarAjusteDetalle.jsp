<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="controladores.ArticulosControlador"%>
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
    // int total_ajuste= con + 0; 

    //int con=total_detalleajuste;
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setId_ajuste_stock_detalle(id_ajuste_stock_detalle);
    detalleajuste.setCantidad_ajuste_stock(cantidad_ajuste_stock);

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);

    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    detalleajuste.setAjuste(ajuste);
    detalleajuste.setArticulo(articulo);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_ajuste_stock);
    stock.setArticulo(articulo);
    StockControlador.descontar1(stock);
    if (DetallesAjustesControlador.agregar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    //articulo = new Articulos();
    //articulo.setId_articulo(id_articulo);
    /*producto = new Productos();
    producto.setId_producto(id_producto);*/
    //ProductosControlador.modificarc(producto);
    
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();

%>