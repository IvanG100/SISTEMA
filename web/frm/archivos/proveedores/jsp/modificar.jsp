<%@page import="modelos.Ciudades"%>
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_proveedor = request.getParameter("nombre_proveedor");
    String ruc_proveedor = request.getParameter("ruc_proveedor");
    String direccion_proveedor = request.getParameter("direccion_proveedor");
    String telefono_proveedor = request.getParameter("telefono_proveedor");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    proveedor.setNombre_proveedor(nombre_proveedor);
    proveedor.setRuc_proveedor(ruc_proveedor);
    proveedor.setDireccion_proveedor(direccion_proveedor);
    proveedor.setTelefono_proveedor(telefono_proveedor);
    proveedor.setCiudad(ciudad);
    
    if (ProveedoresControlador.modificar(proveedor)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
