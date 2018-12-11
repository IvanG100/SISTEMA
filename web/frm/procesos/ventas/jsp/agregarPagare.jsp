
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="controladores.PagaresControlador"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.Pagares"%>
<%@page import="modelos.CuentasClientes"%>
<%@page import="modelos.Recibos"%>
<%@page import="controladores.RecibosControlador"%>
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pagare = 0;
    
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    //String nro_cuota = request.getParameter("cantidad_cuotas");
    //String stotal = request.getParameter("total");
    //String montoenletras = Utiles.cantidadConLetra(stotal);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
   FacturaVentas ventas = new FacturaVentas();
    ventas.setId_factura_venta(id_factura_venta);
   
    
    
    //recibo.setMontoenletras(montoenletras);
    FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalf(id_factura_venta);
    if (facturaventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        //nuevo = "false";
    } else {
        facturaventa = new FacturaVentas();
        facturaventa.setId_factura_venta(0);
        facturaventa.setTotal(0);
    }
    int monto = facturaventa.getTotal();
    String montoaconvertir = String.valueOf(monto);
    String montoenletras = Utiles.cantidadConLetra(montoaconvertir);
    Pagares pagare = new Pagares();
    pagare.setId_pagare(id_pagare);
    pagare.setVentas(ventas);
    pagare.setMontoenletras(montoenletras);
    
    
    if (PagaresControlador.agregar(pagare)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_factura_venta", facturaventa.getId_factura_venta());
    System.out.println("idfactura==" + facturaventa.getId_factura_venta());
    obj.put("total", facturaventa.getTotal());
    //obj.put("id", recibo.getId_recibo());
    out.print(obj);
    out.flush();
%>

