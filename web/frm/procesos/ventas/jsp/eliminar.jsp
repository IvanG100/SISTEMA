<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="controladores.FacturaDetalleVentasControlador"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="controladores.FacturaVentasControlador"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));

    String tipo = "error";
    String mensaje = "Datos no anulados.";

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setId_factura_venta(id_factura_venta);
    
    
    Stock stock = new Stock();
    stock.setId_stock(id_factura_venta);
    StockControlador.sumarStock(stock);
    //FacturaDetalleVentasControlador.eliminarArticuloVentas(facturaventa);

    if (FacturaVentasControlador.Anular(facturaventa)) {
        tipo = "success";
        mensaje = "Datos anulados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>