<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Puestos"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    int desde_timbrado = Integer.parseInt(request.getParameter("desde_timbrado"));
    int hasta_timbrado = Integer.parseInt(request.getParameter("hasta_timbrado"));
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String estado_timbrado = request.getParameter("estado_timbrado");
    String sfecha_inicio_timbrado = request.getParameter("fecha_inicio_timbrado");
    String sfecha_vencimiento_timbrado = request.getParameter("fecha_vencimiento_timbrado");
    //String sfecha_actual_timbrado = request.getParameter("fecha_actual_timbrado");
    
    java.sql.Date fecha_inicio_timbrado = Utiles.stringToSqlDate(sfecha_inicio_timbrado);
    java.sql.Date fecha_vencimiento_timbrado = Utiles.stringToSqlDate(sfecha_vencimiento_timbrado);
    //java.sql.Date fecha_actual_timbrado = Utiles.stringToSqlDate(sfecha_actual_timbrado);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio_timbrado(fecha_inicio_timbrado);
    timbrado.setFecha_vencimiento_timbrado(fecha_vencimiento_timbrado);
    //timbrado.setFecha_actual_timbrado(fecha_actual_timbrado);
    timbrado.setDesde_timbrado(desde_timbrado);
    timbrado.setHasta_timbrado(hasta_timbrado);
    timbrado.setEstado_timbrado(estado_timbrado);
    timbrado.setPuesto(puesto);
    timbrado.setEstablecimiento(establecimiento);
    
    if (TimbradosControlador.modificar(timbrado)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
