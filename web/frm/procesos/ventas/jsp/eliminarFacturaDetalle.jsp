<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.Articulos"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="modelos.FacturaDetalle"%>
<%@page import="controladores.FacturaDetalleVentasControlador"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_detalle_venta = Integer.parseInt(request.getParameter("id_factura_detalle_venta"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    //int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    //FacturaVentas facturaventa = new FacturaVentas();
    //facturaventa.setId_factura_venta(id_factura_venta);

    FacturaDetalle facturadetalle = new FacturaDetalle();
    facturadetalle.setId_factura_detalle_venta(id_factura_detalle_venta);
    //facturadetalle.setFacturaventa(facturaventa);

    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_venta);
    stock.setArticulo(articulo);
    StockControlador.sumar(stock);
    if (FacturaDetalleVentasControlador.eliminar(facturadetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>