<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.Clientes"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    int cantidad_cuotas = Integer.parseInt(request.getParameter("cantidad_cuotas"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(id_tipo_factura);

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setId_factura_venta(id_factura_venta);
    facturaventa.setCliente(cliente);
    facturaventa.setTipofactura(tipofactura);
    facturaventa.setCantidad_cuotas(cantidad_cuotas);
   
    if (FacturaVentasControlador.modificar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>