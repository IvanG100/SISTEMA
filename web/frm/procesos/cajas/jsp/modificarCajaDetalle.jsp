
<%@page import="todomueble.controladores.DetallesCuentasControlador"%>
<%@page import="todomueble.modelos.Cuentas"%>
<%@page import="todomueble.modelos.Ventas"%>
<%@page import="todomueble.modelos.DetallesCuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detallecuenta = Integer.parseInt(request.getParameter("id_detallecuenta"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
  //  int costo_cuenta = Integer.parseInt(request.getParameter("costo_cuenta"));    
  //  int total_detallecuenta = cantidad_cuentaventa * costo_cuenta;
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesCuentas detallecuenta = new DetallesCuentas();
    detallecuenta.setId_detallecuenta(id_detallecuenta);
    detallecuenta.setImporte(importe);
   
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    
    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    
    detallecuenta.setVenta(venta);
    detallecuenta.setCuenta(cuenta);
    
    if (DetallesCuentasControlador.modificar(detallecuenta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>