function fechaHoy() {

    var hoy = new new Date().toJSON().slice(0, 10);



    console.log(hoy);
    //$("#fecha_actual_timbrado").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function agregarTimbrado() {
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
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_timbrado").focus();
        }
    });
}

function modificarTimbrado() {
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
            limpiarFormulario();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarTimbrado() {
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
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_timbrado").val(json.id_timbrado);
            $("#numero_timbrado").val(json.numero_timbrado);
            $("#fecha_inicio_timbrado").val(json.fecha_inicio_timbrado);
            $("#fecha_vencimiento_timbrado").val(json.fecha_vencimiento_timbrado);
            //$("#fecha_actual_timbrado").val(json.fecha_actual_timbrado);
            $("#desde_timbrado").val(json.desde_timbrado);
            $("#hasta_timbrado").val(json.hasta_timbrado);
            $("#estado_timbrado").val(json.estado_timbrado);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            var fecha = $("#fecha_factura_venta").serialize();

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#numero_timbrado", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#numero_timbrado", "#botonModificar", true);
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

function buscarNumeroTimbrado() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNumero.jsp',
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
                $("#id_timbrado").val(id);
                $("#numero_timbrado").focus();
                buscarIdTimbrado();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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

function buscarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);


            if (json.nuevo === "false") {
                $("#numero_timbrado").val("");
                $("#numero_timbrado").focus();
            } else {

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

function validarFormulario() {
    var valor = true;
    if ($("#numero_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nª timbrado no puede estar vacio.");
        $("#numero_timbrado").focus();
    }

    if ($("#fecha_inicio_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha inicio no puede estar vacio.");
        $("#fecha_inicio_timbrado").focus();
    }

    if ($("#fecha_vencimiento_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha vencimiento no puede estar vacio.");
        $("#fecha_vencimiento_timbrado").focus();
    }

    /*if ($("#fecha_actual_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha actual no puede estar vacio.");
        $("#fecha_actual_timbrado").focus();
    }*/
    
    if ($("#desde_timbrado").val().trim() === "" || $("#desde_timbrado").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Desde timbrado no puede estar vacio.");
        $("#desde_timbrado").focus();
    }
    
    if ($("#hasta_timbrado").val().trim() === "" || $("#hasta_timbrado").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Hasta timbrado no puede estar vacio.");
        $("#hasta_timbrado").focus();
    }
    
    if ($("#estado_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Estado timbrado no puede estar vacio.");
        $("#estado_timbrado").focus();
    }
    
    if ($("#id_puesto").val().trim() === "" || $("#id_puesto").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Puesto no puede estar vacio.");
        $("#id_puesto").focus();
    }
    
    if ($("#id_establecimiento").val().trim() === "" || $("#id_establecimiento").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Establecimiento no puede estar vacio.");
        $("#id_establecimiento").focus();
    }
    
    

    return valor;
}

function limpiarFormulario() {
    $("#id_timbrado").val("");
    $("#numero_timbrado").val("");
    $("#fecha_inicio_timbrado").val("");
    $("#fecha_vencimiento_timbrado").val("");
    //$("#fecha_actual_timbrado").val("");
    $("#desde_timbrado").val("");
    $("#hasta_timbrado").val("");
    $("#estado_timbrado").val("");
    $("#id_puesto").val("");
    $("#nombre_puesto").val("");
    $("#id_establecimiento").val("");
    $("#nombre_establecimiento").val("");
}

function buscarIdEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstablecimiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#actividad_economica").val(json.actividad_economica);
            $("#ruc_establecimiento").val(json.ruc_establecimiento);
            $("#representante_establecimiento").val(json.representante_establecimiento);
            $("#direccion_establecimiento").val(json.direccion_establecimiento);
            $("#telefono_establecimiento").val(json.telefono_establecimiento);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
  
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
function buscarNombreEstablecimiento() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstablecimiento.jsp',
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
                $("#id_establecimiento").val(id);
                $("#nombre_establecimiento").focus();
                buscarIdEstablecimiento();
                $("#buscar").fadeOut("slow");
                $("#panelAcceso").fadeIn("slow");
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

function buscarIdPuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPuesto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
            
            
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

function buscarNombrePuesto() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombrePuesto.jsp',
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
              $("#id_puesto").val(id);
              $("#nombre_puesto").focus();
              buscarIdPuesto();
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
