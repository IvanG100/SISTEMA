<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Puestos"%>
<%@page import="modelos.Timbrados"%>
<%@page import="controladores.FacturaDetalleVentasControlador"%>
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="modelos.Clientes"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
     //String sfecha_factura_venta = request.getParameter("fecha_factura_venta");
    //java.sql.Date fecha_factura_venta = Utiles.stringToSqlDate(sfecha_factura_venta);
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_venta = request.getParameter("ruc_factura_venta");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    //java.sql.Date ssFecha_factura_venta = new java.sql.Date(new java.util.Date().getTime());
    FacturaVentas facturaventa = FacturaVentasControlador.buscarId(id_factura_venta);
    if (facturaventa.getId_factura_venta()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    
        
    
    String contenido_detalle = FacturaDetalleVentasControlador.buscarIdFacturaVenta(id_factura_venta);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("id", id_factura_venta);
    System.out.println("idfactura"+id_factura_venta);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_factura_venta", facturaventa.getId_factura_venta());
    obj.put("fecha_factura_venta", String.valueOf(facturaventa.getFecha_factura_venta()));
    obj.put("id_cliente", facturaventa.getCliente().getId_cliente());
    obj.put("nombre_cliente", facturaventa.getCliente().getNombre_cliente());
    obj.put("ruc_cliente", facturaventa.getCliente().getRuc_cliente());
    obj.put("id_tipo_factura", facturaventa.getTipofactura().getId_tipo_factura());
    obj.put("nombre_tipo_factura", facturaventa.getTipofactura().getNombre_tipo_factura());
    /*obj.put("subtotal_5", facturaventa.getSubtotal_5());
    obj.put("subtotal_10", facturaventa.getSubtotal_10());
    obj.put("subtotal_exenta", facturaventa.getSubtotal_exenta());*/
    obj.put("cantidad_cuotas", facturaventa.getCantidad_cuotas());
    obj.put("numero_factura_venta", facturaventa.getNumero_factura_venta());
    
    obj.put("id_timbrado", facturaventa.getTimbrado().getId_timbrado());
    System.out.println("idtimbrado= " + facturaventa.getTimbrado().getId_timbrado());
    obj.put("id_puesto", facturaventa.getTimbrado().getPuesto().getId_puesto());
    obj.put("id_establecimiento", facturaventa.getTimbrado().getEstablecimiento().getId_establecimiento());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>