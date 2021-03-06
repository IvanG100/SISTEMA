

<%@page import="modelos.Ubicaciones"%>
<%@page import="modelos.Stock"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="modelos.Marcas"%>
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    int iva_articulo = Integer.parseInt(request.getParameter("iva_articulo"));
    String nombre_articulo = request.getParameter("nombre_articulo");
    String descripcion_articulo = request.getParameter("descripcion_articulo");
    //int precio_unitario = Integer.parseInt(request.getParameter("precio_unitario"));
    String precio_v = request.getParameter("precio_venta").replaceAll("\\.","");
    int precio_venta = Integer.parseInt(precio_v);
    String precio_c = request.getParameter("precio_compra").replaceAll("\\.","");
    int precio_compra = Integer.parseInt(precio_c);
    int id_ubicacion = Integer.parseInt(request.getParameter("id_ubicacion"));
    //String iva_5 = request.getParameter("iva_5");
    
    String codigo_articulo = request.getParameter("codigo_articulo");
    int cantidad_stock = 0;
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    
    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);
    
    Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_ubicacion);
    
    
    
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    articulo.setNombre_articulo(nombre_articulo);
    articulo.setDescripcion_articulo(descripcion_articulo);
    //articulo.setPrecio_unitario(precio_unitario);
    articulo.setPrecio_venta(precio_venta);
    articulo.setPrecio_compra(precio_compra);
    articulo.setIva_articulo(iva_articulo);
    //articulo.setIva_5(iva_5);
    
    articulo.setCodigo_articulo(codigo_articulo);
    articulo.setMarca(marca);
    articulo.setCategoria(categoria);
    articulo.setUbicacion(ubicacion);
    
    if (ArticulosControlador.agregar(articulo)) {
        tipo = "success";
        mensaje = "Datos agregados.";
        Stock stock = new Stock();
        stock.setCantidad_stock(cantidad_stock);
        stock.setArticulo(articulo);
        stock.setUbicacion(ubicacion);
        StockControlador.agregar(stock);
        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_articulo", articulo.getId_articulo());
    out.print(obj);
    out.flush();
%>

