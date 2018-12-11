
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.Formas_pagos"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCajas detallecaja = DetallesCajasControlador.buscarId(id_detallecaja);
    if (detallecaja != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecaja = new DetallesCajas();
        detallecaja.setId_detallecaja(0);
        detallecaja.setImporte(0);

        Cajas caja = new Cajas();
        caja.setId_caja(0);
        detallecaja.setCaja(caja);

        FacturaVentas facturaventa = new FacturaVentas();
        facturaventa.setId_factura_venta(0);
        detallecaja.setFacturaventa(facturaventa);

        Formas_pagos pago = new Formas_pagos();
        pago.setId_forma_pago(0);
        pago.setNombre_forma_pago("");
        detallecaja.setFacturaventa(facturaventa);
    }
    //int id_factura_venta = detallecaja.getFacturaventa().getId_factura_venta();
    //System.out.println("idfactu "+ id_factura_venta);
    //FacturaVentas facturaventa = new FacturaVentas();
    //FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalfactura(id_factura_venta);
    //int total1 = facturaventa.getTotal();
    //System.out.println("total1 "+ total1);
    //int importe = detallecaja.getImporte();
    //int vuelto1 = importe - total1;

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallecaja", String.valueOf(detallecaja.getId_detallecaja()));
    obj.put("id_caja", String.valueOf(detallecaja.getCaja().getId_caja()));
    System.out.println("cajaid= "+String.valueOf(detallecaja.getCaja().getId_caja()));
    obj.put("id_factura_venta", String.valueOf(detallecaja.getFacturaventa().getId_factura_venta()));
    //obj.put("numero_factura_venta", String.valueOf(detallecaja.getFacturaventa().getNumero_factura_venta()));
    obj.put("id_forma_pago", detallecaja.getPago().getId_forma_pago());
    //obj.put("nombre_forma_pago", detallecaja.getPago().getNombre_forma_pago());
    obj.put("importe", String.valueOf(detallecaja.getImporte()));
    //obj.put("vuelto", (vuelto1));
    //obj.put("total", facturaventa.getTotal());
    //System.out.println("total "+ facturaventa.getTotal());
    //System.out.println("vuelto "+ vuelto1);
    out.print(obj);
    out.flush();
%>
