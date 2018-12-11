<%@page import="modelos.CuentasClientesDetalle"%>
<%@page import="controladores.DetallesCuentasClientesControlador"%>
<%@page import="modelos.CuentasClientes"%>
<%@page import="controladores.CuentasClientesControlador"%>
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.Formas_pagos"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int nro_cuota = Integer.parseInt(request.getParameter("nro_cuota"));
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    //int id_factura_venta1 = Integer.parseInt(request.getParameter("sid_factura_venta"));
    //int numero_factura_venta = Integer.parseInt(request.getParameter("numero_factura_venta"));
    int total = Integer.parseInt(request.getParameter("total"));
    //int total1 = Integer.parseInt(request.getParameter("stotal"));
    int id_forma_pago = Integer.parseInt(request.getParameter("id_forma_pago"));
    String nombre_forma_pago = request.getParameter("nombre_forma_pago");
    int totald = importe - total;
    String tipo = "error";
    
    String mensaje = "Datos no agregados.";
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setVuelto(totald);
    detallecaja.setImporte(importe);
    //  detallecaja.setCantidad_cajaventa(cantidad_cajaventa);

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setId_factura_venta(id_factura_venta);
    //facturaventa.setNumero_factura_venta(numero_factura_venta);
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
    Formas_pagos pago = new Formas_pagos();
    pago.setId_forma_pago(id_forma_pago);
    pago.setNombre_forma_pago(nombre_forma_pago);
    detallecaja.setFacturaventa(facturaventa);
    detallecaja.setCaja(caja);
    detallecaja.setPago(pago);
    CuentasClientes cuentasclientes = new CuentasClientes();
    cuentasclientes.setId_cuenta(id_cuenta);
    cuentasclientes.setNro_cuota(nro_cuota);
    CuentasClientesDetalle cuentasClientesDetalle = new CuentasClientesDetalle();
    cuentasClientesDetalle.setCuentasclientes(cuentasclientes);
    //int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
        if (id_cuenta != 0 || String.valueOf(id_cuenta) != "" && nro_cuota != 0 || String.valueOf(nro_cuota) != ""){
            //FacturaVentasControlador.modificarestadocobro(facturaventa);
        //}else{
            DetallesCuentasClientesControlador.modificar(cuentasClientesDetalle);
            CuentasClientesControlador.modificar(cuentasclientes);
            
        }
    //FacturaVentasControlador.modificarestadocobro(facturaventa);
    FacturaVentasControlador.modificarestadocobro(facturaventa);
    if (DetallesCajasControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    facturaventa = new FacturaVentas();
    facturaventa.setId_factura_venta(id_factura_venta);
    facturaventa.setNumero_factura_venta(0);
    pago = new Formas_pagos();
    pago.setId_forma_pago(id_forma_pago);
    pago.setNombre_forma_pago(nombre_forma_pago);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
    
%>