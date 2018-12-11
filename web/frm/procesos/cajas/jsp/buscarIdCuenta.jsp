<%@page import="modelos.CuentasClientes"%>
<%@page import="controladores.CuentasClientesControlador"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="controladores.FacturaDetalleVentasControlador"%>
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="modelos.Clientes"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
     //String sfecha_factura_venta = request.getParameter("fecha_factura_venta");
    //java.sql.Date fecha_factura_venta = Utiles.stringToSqlDate(sfecha_factura_venta);
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_venta = request.getParameter("ruc_factura_venta");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    CuentasClientes cuentasclientes = new CuentasClientes();
    cuentasclientes.setId_cuenta(id_cuenta);
    //FacturaVentas facturaventa = new FacturaVentas();
    //facturaventa.setId_factura_venta(id_factura_venta);

    //java.sql.Date ssFecha_factura_venta = new java.sql.Date(new java.util.Date().getTime());
    //System.out.println("idfac "+id_factura_venta);
    CuentasClientesControlador.buscarId1(cuentasclientes);
    if (id_cuenta != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";    
    }
   /*int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    DetallesCajas detallecaja = DetallesCajasControlador.buscarId(id_detallecaja);
    int total1 = facturaventa.getTotal();
    System.out.println("total1 "+ total1);
    int importe = detallecaja.getImporte();
    int vuelto1 = importe - total1;*/
    
    /*FacturaVentas facturaventa = FacturaVentasControlador.buscarId(id_factura_venta);
    if (facturaventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturaventa = new FacturaVentas();
        facturaventa.setId_factura_venta(0);
        //facturaventa.setFecha_factura_venta(ssFecha_factura_venta);
        /*facturaventa.setSubtotal_5(0);
        facturaventa.setSubtotal_10(0);
        facturaventa.setSubtotal_exenta(0);
        facturaventa.setCantidad_cuotas(0);
  
        Clientes cliente = new Clientes();
        facturaventa.setCliente(cliente);
        
        Tipo_facturas tipofactura = new Tipo_facturas();
        facturaventa.setTipofactura(tipofactura);
        }*/
        
    
        
    
    //String contenido_detalle = FacturaDetalleVentasControlador.buscarIdFacturaVenta(id_factura_venta);
    //FacturaVentasControlador.buscarId(id_factura_venta);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    //obj.put("id_factura_venta", id_factura_venta);
    //System.out.println("idfactura"+id_factura_venta);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cuenta", cuentasclientes.getId_cuenta());
    obj.put("nro_cuota", cuentasclientes.getNro_cuota());
    obj.put("id_factura_venta", cuentasclientes.getFacturaventa().getId_factura_venta());
    obj.put("total", cuentasclientes.getMonto_cuota());
    //System.out.println("total "+ facturaventa.getTotal());
    //obj.put("fecha_factura_venta", String.valueOf(facturaventa.getFecha_factura_venta()));
    //obj.put("id_cliente", facturaventa.getCliente().getId_cliente());
    //obj.put("nombre_cliente", facturaventa.getCliente().getNombre_cliente());
    //obj.put("ruc_cliente", facturaventa.getCliente().getRuc_cliente());
    //obj.put("id_tipo_factura", facturaventa.getTipofactura().getId_tipo_factura());
    //obj.put("nombre_tipo_factura", facturaventa.getTipofactura().getNombre_tipo_factura());
    /*obj.put("subtotal_5", facturaventa.getSubtotal_5());
    obj.put("subtotal_10", facturaventa.getSubtotal_10());
    obj.put("subtotal_exenta", facturaventa.getSubtotal_exenta());*/
    //obj.put("cantidad_cuotas", facturaventa.getCantidad_cuotas());
    //obj.put("contenido_detalle", contenido_detalle);
   /* obj.put("vuelto", (vuelto1));
    System.out.println("vuelto "+ vuelto1);*/
    out.print(obj);
    out.flush();
%>