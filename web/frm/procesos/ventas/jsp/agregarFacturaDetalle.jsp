<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="controladores.FacturaDetalleVentasControlador"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.FacturaDetalle"%>

<%@page import="modelos.Articulos"%>
<%--<%@page import="modelos.Pedidos"%>--%>
<%--<%@page import="modelos.DetallesPedidos"%>--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));

    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    //int subtotal_venta = Integer.parseInt(request.getParameter("subtotal_venta"));
    /*int subtotal_5 = Integer.parseInt(request.getParameter("ssubtotal_5"));
    int subtotal_10 = Integer.parseInt(request.getParameter("ssubtotal_10"));
    int subtotal_exenta = Integer.parseInt(request.getParameter("ssubtotal_exenta"));*/
    
    
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FacturaDetalle facturadetalle = new FacturaDetalle();
    //detallepedido.setId_detallepedido(id_detallepedido);
    facturadetalle.setCantidad_venta(cantidad_venta);
    //facturadetalle.setSubtotal_venta(subtotal_venta);

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setId_factura_venta(id_factura_venta);
    /*facturaventa.setSubtotal_10(subtotal_10);
    facturaventa.setSubtotal_5(subtotal_5);
    facturaventa.setSubtotal_exenta(subtotal_exenta);*/
    

    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);

    facturadetalle.setFacturaventa(facturaventa);
    facturadetalle.setArticulo(articulo);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_venta);
    stock.setArticulo(articulo);
    StockControlador.descontar(stock);
    
    
    if (stock.getCantidad_stock()!=-1){
        //FacturaVentasControlador.subtotaliva(facturaventa);
        if (FacturaDetalleVentasControlador.agregar(facturadetalle)) {
        
        tipo = "success";
        mensaje = "Datos agregados.";
        }
    }else{
        tipo = "success";
        mensaje = "Stock insuficiente";
    }
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();

%>