<%@page import="controladores.FacturaDetalleVentasControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.FacturaDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_factura_detalle_venta = Integer.parseInt(request.getParameter("id_factura_detalle_venta"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    int subtotal_venta = Integer.parseInt(request.getParameter("subtotal_venta"));
   int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    FacturaDetalle facturadetalle = new FacturaDetalle();
    facturadetalle.setId_factura_detalle_venta(id_factura_detalle_venta);
    facturadetalle.setCantidad_venta(cantidad_venta);
    facturadetalle.setSubtotal_venta(subtotal_venta);
    
    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setId_factura_venta(id_factura_venta);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
    facturadetalle.setFacturaventa(facturaventa);
    facturadetalle.setArticulo(articulo);
      
    if (FacturaDetalleVentasControlador.modificar(facturadetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>