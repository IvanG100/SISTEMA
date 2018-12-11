function agregarEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
   // alert(datosFormulario);
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
            limpiarCampos();
            $("#id_establecimiento").focus();
            $("#id_establecimiento").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_establecimiento").focus();
        }
    });
}
function buscarIdEstablecimiento() {
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
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#actividad_economica").val(json.actividad_economica);
            $("#ruc_establecimiento").val(json.ruc_establecimiento);
            $("#representante_establecimiento").val(json.representante_establecimiento);
            $("#direccion_establecimiento").val(json.direccion_establecimiento);
            $("#telefono_establecimiento").val(json.telefono_establecimiento);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
  if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_establecimiento", "#botonAgregar", false);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#nombre_establecimiento", "#botonAgregar", false);
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
function buscarNombreEstablecimiento() {
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
function modificarEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarCampos();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            limpiarCampos();
            $("#id_establecimiento").focus();
            $("#id_establecimiento").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            limpiarCampos();
            $("#id_establecimiento").focus();
            $("#id_establecimiento").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_establecimiento").val("0");
    $("#nombre_establecimiento").val("");
    $("#actividad_economica").val("");
    $("#ruc_establecimiento").val("");
    $("#representante_establecimiento").val("");
    $("#direccion_establecimiento").val("");
    $("#telefono_establecimiento").val("");
    $("#id_establecimiento").focus();
}
function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', false);
                $("#botonEliminar").prop('disable', false);
                siguienteCampo("#nombre_ciudad", "#botonmodificar", true);

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
function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
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
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
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
        return false;
    }
}
function validarFormulario() {
    var valor = true;
    if ($("#nombre_establecimiento").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre establecimientos no puede estar vacio.");
        $("#nombre_establecimiento").focus();
    }
     if ($("#actividad_economica").val().length < 3) {
        valor = false;
        $("#mensajes").html(" actividad_economica no puede estar vacio.");
        $("#actividad_economica").focus();
    }
    if ($("#ruc_establecimiento").val().length < 3) {
        valor = false;
        $("#mensajes").html(" ruc no puede estar vacio.");
        $("#ruc_establecimiento").focus();
    }
    if ($("#representante_establecimiento").val().length < 3) {
        valor = false;
        $("#mensajes").html(" representante no puede estar vacio.");
        $("#representante_establecimiento").focus();
    }
    if ($("#direccion_establecimiento").val().length < 3) {
        valor = false;
        $("#mensajes").html(" direccion no puede estar vacio.");
        $("#direccion_establecimiento").focus();
    }
    if ($("#telefono_establecimiento").val().length < 3) {
        valor = false;
        $("#mensajes").html(" telefono no puede estar vacio.");
        $("#telefono_establecimiento").focus();
    }
    return valor;
}
function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode();
	return (key >= 48 && key <= 57);
}