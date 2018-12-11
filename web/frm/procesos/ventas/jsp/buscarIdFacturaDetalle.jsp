<%@page import="modelos.Ivas"%>
<%@page import="controladores.FacturaDetalleVentasControlador"%>
<%@page import="modelos.FacturaDetalle"%>
<%@page import="modelos.Articulos"%>
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.FacturaVentas"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_detalle_venta = Integer.parseInt(request.getParameter("id_factura_detalle_venta"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaDetalle facturadetalle = FacturaDetalleVentasControlador.buscarId(id_factura_detalle_venta);
    if (facturadetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturadetalle = new FacturaDetalle();
        facturadetalle.setId_factura_detalle_venta(0);
        facturadetalle.setSubtotal_venta(0);
        
        FacturaVentas facturaventa = new FacturaVentas();
        facturaventa.setId_factura_venta(0);
        facturadetalle.setFacturaventa(facturaventa);
        
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_articulo("");
        
        facturadetalle.setArticulo(articulo);
        
        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_factura_detalle_venta", String.valueOf(facturadetalle.getId_factura_detalle_venta()));
    obj.put("id_factura_venta", String.valueOf(facturadetalle.getFacturaventa().getId_factura_venta()));
    obj.put("id_articulo", String.valueOf(facturadetalle.getArticulo().getId_articulo()));
    obj.put("nombre_articulo", facturadetalle.getArticulo().getNombre_articulo());
    obj.put("precio_venta", facturadetalle.getArticulo().getPrecio_venta());
    obj.put("precio_compra", facturadetalle.getArticulo().getPrecio_compra());
    //obj.put("id_iva", facturadetalle.getArticulo().getIva().getId_iva());
    obj.put("iva_articulo", facturadetalle.getArticulo().getIva_articulo());
    obj.put("cantidad_venta", String.valueOf(facturadetalle.getCantidad_venta()));
    //obj.put("subtotal_venta", String.valueOf(facturadetalle.getSubtotal_venta()));
    
    out.print(obj);
    out.flush();
%>