function fechaHoy(){

var hoy = new  new Date().toJSON().slice(0,10);



console.log(hoy);
 $("#fecha_factura_venta").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}



function buscarIdFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_factura_venta").val(json.id_factura_venta);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#fecha_factura_venta").val(json.fecha_factura_venta);
            $("#id_tipo_factura").val(json.id_tipo_factura);
            $("#nombre_tipo_factura").val(json.nombre_tipo_factura);
         
            $("#cantidad_cuotas").val(json.cantidad_cuotas);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#id_timbrado").val(json.id_timbrado);
            $("#id_puesto").val(json.id_puesto);
            $("#id_establecimiento").val(json.id_establecimiento);
           var a = 0;
                                        var b =0;
                                            while (a === 0){
                                             b = b + 1;   
                                            $("#id_timbrado").val(b);
                                            buscarIdTimbrado();
                                            a=$("#id_timbrado").val();
                                            }
                                        
             //var fecha = $("#fecha_factura_venta").serialize();
             

            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonCuenta").prop('disabled', true);
                //siguienteCampo("#id_tipofactura", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                if (json.nombre_tipo_factura === "CONTADO"){
                    $("#botonCuenta").prop('disabled', true);
                }else{
                    if (json.nombre_tipo_factura === "CREDITO"){
                    $("#botonCuenta").prop('disabled', false);
                }
                    
                }
                
                //siguienteCampo("#id_tipopedido", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreFacturaVenta() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_factura_venta").val(id);
                $("#nombre_cliente").focus();
                buscarIdFacturaVenta();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_factura_venta").val(json.id_factura_venta);
            buscarIdFacturaVenta();
            if (json.nombre_tipo_factura === "CONTADO") {
                
                $("#botonCuenta").prop('disabled', true);
            } else {
                
                $("#botonCuenta").prop('disabled', false);
            }
            // $("#id_pedido").focus;
            //$("#id_pedido").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function agregarCuenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //limpiarFormulario();
            
            $("#mensajes").html(json.mensaje);
            if (n > 0){
                $("#mensajes").html("Cuenta agregada");
            }
            //alert("logrado");
            $("#botonCuenta").prop('disabled', true);
            //$("#detalle").prop('hidden', false);
            //$("#id_factura_venta").val(json.id_factura_venta);
            //buscarIdFacturaVenta();
            
             $("#id_factura_venta").focus;
            $("#id_factura_venta").select();
            
            buscarIdFacturaVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar la cuenta.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function modificarFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_factura_venta").focus;
            $("#id_factura_venta").select();
            buscarIdFacturaVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarFacturaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_factura_venta").focus;
            $("#id_factura_venta").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function validarFormulario() {
    var valor = true;
    if ($("#fecha_factura_venta").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha no puede estar vacio.");
        $("#fecha_factura_venta").focus();
    }
    
    if ($("#id_cliente").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#id_cliente").focus();
    }

    /*if ($("#nombre_cliente").val().length < 2) {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#id_proveedor").focus();
    }*/

    if ($("#id_tipo_factura").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Tipo Factura no puede estar vacio.");
        $("#id_tipo_factura").focus();
    }
    
    if ($("#id_timbrado").val().trim() === "0" || $("#id_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Timbrado no puede estar vacio.");
        $("#id_timbrado").focus();
    }
    
    /*if ($("#cantidad_cuotas").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Cuotas no puede estar vacio.");
        $("#cantidad_cuotas").focus();
    }*/
    
    return valor;
    
    
}


function limpiarFormulario() {
    $("#id_factura_venta").val("");
    $("#fecha_factura_venta").val("");
    //$("#nombre_tipo_factura").val("");
    $("#nombre_cliente").val("");
    $("#id_cliente").val("");
    $("#id_tipo_factura").val("");
    /*$("#subtotal_5").val("");
    $("#subtotal_10").val("");
    $("#subtotal_exenta").val("");*/
    $("#cantidad_cuotas").val("");

}
function agregarLinea() {
    $("#id_factura_detalle_venta").val("0");
    $("#id_articulo").val("0");
    $("#nombre_articulo").val("");
    $("#cantidad_venta").val("0");
    $("#precio_venta").val("0");
    $("#precio_compra").val("0");
    $("#iva_articulo").val("0");
    $("#subtotal_venta").val("0");
    /*$("#ssubtotal_5").val("0");
    $("#ssubtotal_10").val("0");
    $("#ssubtotal_exenta").val("0");*/
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_articulo").focus();
    $("#id_articulo").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_factura_detalle_ventas", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_factura_detalle_venta").val(id);
    $("#id_articulo").val("0");
    $("#nombre_articulo").val("");
    $("#cantidad_venta").val("0");
    $("#precio_venta").val("0");
    $("#precio_compra").val("0");
    $("#iva_articulo").val("0");
    $("#subtotal_venta").val("0");
    /*$("#ssubtotal_5").val("0");
    $("#ssubtotal_10").val("0");
    $("#ssubtotal_exenta").val("0");*/
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_articulo").focus();
    $("#id_articulo").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdFacturaDetalle();
    //siguienteCampo("#cantidad_venta", "#botonModificarLinea", true);
}
// pedidosarticulos
function buscarIdFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_articulo").val(json.id_articulo);
            $("#nombre_articulo").val(json.nombre_articulo);
            $("#precio_venta").val(json.precio_venta);
            $("#precio_compra").val(json.precio_compra);
            $("#iva_articulo").val(json.iva_articulo);
            $("#cantidad_venta").val(json.cantidad_venta);
            //$("#subtotal_venta").val(json.subtotal_venta);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
/*function buscarIdFacturaFacturaDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFacturaFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoDetalle").html(json.contenido_detalle);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}*/
function agregarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    
    var id_factura_venta = $("#id_factura_venta").val();
    datosFormulario += "&id_factura_venta=" + id_factura_venta;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.cantidad_stock !== -1){
                $("#mensajes").html(json.mensaje);
                $("#panelLinea").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            buscarIdFacturaVenta();
            } else {
                
                $("#mensajes").html(json.mensaje);
                $("#cantidad_venta").val("");
                $("#cantidad_venta").focus();
            }
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura_venta = $("#id_factura_venta").val();
    datosFormulario += "&id_factura_venta=" + id_factura_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFacturaVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura_venta = $("#id_factura_venta").val();
    datosFormulario += "&id_factura_venta=" + id_factura_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFacturaVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// articulos
function buscarIdArticulo() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdArticulo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
           var venta = dar_formato_numero(json.precio_venta, ",", ".");
            var compra = dar_formato_numero(json.precio_compra, ",", ".");
            $("#id_articulo").val(json.id_articulo);
            $("#nombre_articulo").val(json.nombre_articulo);
            $("#precio_venta").val(venta);
            $("#precio_compra").val(compra);
            $("#iva_articulo").val(json.iva_articulo);
            
           // alert(json.codigo_articulo);
          /* $("#subtotal_5").val("");
        $("#subtotal_10").val("");
        $("#subtotal_exenta").val("");
        $("#subtotal_venta").val("");
        $("#cantidad_venta").val("");*/
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreArticulo() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreArticulo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_articulo").val(id);
                $("#nombre_articulo").focus();
                buscarIdArticulo();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdTipo_factura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipoFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipo_factura").val(json.id_tipo_factura);
            $("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            if (json.nombre_tipo_factura === "CONTADO") {
                
                $("#cuota").prop('hidden', true);
            } else {
                
                $("#cuota").prop('hidden', false);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreTipo_factura() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreTipoFactura.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_tipo_factura").val(id);
              $("#nombre_tipo_factura").focus();
              buscarIdTipo_factura();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo recuperar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function subtotal(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, total;
    valor = $("#precio_venta").val();
    valor1 = $("#cantidad_venta").val();
    total = valor * valor1;
    $("#subtotal_venta").val(total);
    //iva();
    
}

/*function iva(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, total, iva5,iva10;
    valor = $("#iva_articulo").val();
    valor1 = $("#subtotal_venta").val();
    iva5= valor1 / 21;
    iva10=valor1 / 11;
    total = (valor1 * valor)/100;
    
    if ($("#iva_articulo").val().trim() === "10"){
        
        $("#ssubtotal_10").val(iva10);
        $("#ssubtotal_5").val(0);
        $("#ssubtotal_exenta").val(0);
    }else{
        if ($("#iva_articulo").val().trim() === "5"){
        
        $("#ssubtotal_5").val(iva5);
        $("#ssubtotal_10").val(0);
        $("#ssubtotal_exenta").val(0);
        }else{
            $("#ssubtotal_5").val(0);
        $("#ssubtotal_10").val(0);
        $("#ssubtotal_exenta").val(0);
        }
    }
}*/

function validarcantidad() {
    var valor = true;
    if ($("#cantidad_venta").val().trim() === "" || $("#cantidad_venta").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cantidad debe ser mayor a 0.");
        $("#cantidad_venta").focus();
    }
 
    
    return valor;
}

/*function cuotas(e) {
    var valor1;
    valor1 = $("#nombre_tipo_factura").val();
    if (valor1 === "CONTADO" || valor1 === "Contado" || valor1 === "contado") {
      $("#cantidad_cuotas").val(100);
    }else{
       
    }
}*/
function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_timbrado").val(json.id_timbrado);
            //$("#numero_timbrado").val(json.numero_timbrado);
            //$("#fecha_inicio_timbrado").val(json.fecha_inicio_timbrado);
            //$("#fecha_vencimiento_timbrado").val(json.fecha_vencimiento_timbrado);
            //$("#fecha_actual_timbrado").val(json.fecha_actual_timbrado);
            //$("#desde_timbrado").val(json.desde_timbrado);
            //$("#hasta_timbrado").val(json.hasta_timbrado);
            //$("#estado_timbrado").val(json.estado_timbrado);
            $("#id_puesto").val(json.id_puesto);
            //$("#nombre_puesto").val(json.nombre_puesto);
            $("#id_establecimiento").val(json.id_establecimiento);
            //$("#nombre_establecimiento").val(json.nombre_establecimiento);
            var fecha = $("#fecha_factura_venta").serialize();

            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        alert("ingrese solo letras");
        return false;
    }
}
/*function soloLetras(e){
    tecla = (document.all)? e.keyCode : e.which;
    if (tecla===8) return true;
    
    patron =/[A-Za-zñÑ]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
        
}*/

function SoloNumeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {
        alert("Solo ingrese numeros");
        return false;
    }
}

function agregarPagare() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarPagare.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            //$("#id").val(json.id);
            //$("#id_caja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_caja").focus();
        }
    });
}



