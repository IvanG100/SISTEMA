
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String telefono_proveedor = (request.getParameter("telefono_proveedor"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Proveedores proveedor = new Proveedores();
    proveedor.setTelefono_proveedor(telefono_proveedor);
    
   ProveedoresControlador.buscarTelefono(proveedor);
    if (proveedor.getId_proveedor()==0){
        tipo = "success";
        mensaje = "Telefono repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
