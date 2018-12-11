function siguienteCampo(actual, siguiente, preventDefault) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            if (preventDefault) {
                event.preventDefault();
            }
            $(siguiente).focus();
            $(siguiente).select();
        }
    });
}
function enterCampo(actual, ejecutar) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            eval(ejecutar);
        }
    });
}
function validarAcceso() {
    var login = $("#login_usuario").val();
    var clave = $("#clave_usuario").val();
    
    $("mensajes").html("Mensajes del Sistema");
    if (login === "") {
        alert("Ingrese Usuario");
        $("mensajes").html("Usuario no debe estar vacio.");
        setTimeout(' location.reload()', 1500);
        return false;
    } else if (clave === "") {
        alert("Ingrese Contraseña");
        $("mensajes").html("Clave no debe estar vacio.");
        setTimeout(' location.reload()', 1500);
        return false;
    } else {
        validarAccesoAjax();
    }
}
function validarAccesoAjax() {
    var datosFormulario = $("#formAcceso").serialize();
//alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/validarAcceso.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.acceso === "true") {
                location.href = "menu.html";
            } else {
                $("#mensajes").html("Credencial Invalida");
                setTimeout(' location.reload()', 1500);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo conectar con el servidor Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function verificarSesion(programa) {
    var url = 'jsp/verificarSesion.jsp';
    if (programa) {
        url = '../../../jsp/verificarSesion.jsp';
    }
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: url,
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.activo === "false") {
                if (programa) {
                    location.href = "../../../index.html";
                } else {
                    location.href = "index.html";
                }
            }
            $("#snombre_empresa").html("NCElectrodomesticos");
            $("#slogin_usuario").html(json.login_usuario);
            //$("#snombre_usuario").html(json.nombre_usuario);
            $("#sid_usuario").val(json.id_usuario);
            $("#snombre_usuario").val(json.nombre_usuario);
            $("#mensajes").html(json.mensaje);
            
        },
        error: function (e) {
            $("#mensajes").html("ERROR: No se pudo recuperar la sesión.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function cerrarSesion() {
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/cerrarSesion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html("Sesión Cerrada.");
        },
        error: function (e) {
            $("#mensajes").html("No se pudo cerrar la sesión.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function generarMenuPrincipal() {
    var datosFormulario = "";
    $.ajax({
        type: 'POST',
        url: 'jsp/generarMenuPrincipal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#menuPrincipal").html(json.menu_principal);
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo generar el Menú Principal.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function dar_formato_hora(hora) {
    var fhora = "";
    var shora = hora.toString().replace(/\:/g, "");
    var longitud = shora.length - 1;
    if (longitud > 3) {
        longitud = 3;
    }
    for (pos = longitud; pos >= 0; pos--) {
        var chora = shora.charAt(pos);
        fhora = chora + fhora;
        if (pos === 2) {
            fhora = ":" + fhora;
        }
    }
    return fhora;
}

//Solo introducir letras
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
        $("#codigo_articulo".Val(""));
        return false;
    }
}
/*
function buscarIdCliente() {
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
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#ci_cliente").val(json.ci_cliente);
            $("#direccion_cliente").val(json.direccion_cliente);
            $("#telefono_cliente").val(json.telefono_cliente);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_cliente", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_cliente", "#botonModificar", true);
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
}*/

function dar_formato_numero(numero, separador_decimal, separador_miles) {
    var fnumero = "";
    var snumero = numero.toString().replace(/\./g, "");
    snumero = snumero.replace(/[a-z]|_|%/ig, "");
    var pdecimal = snumero.indexOf(",");
    var psigno = snumero.indexOf("-");
    var enumero = snumero;
    var edecimal = "";
    var esigno = "";
    if (psigno !== -1) {
        esigno = "-";
        enumero = snumero.substr(1, snumero.length);
    }
    if (pdecimal !== -1) {
        if (psigno === -1) {
            enumero = snumero.substr(0, pdecimal);
        } else {
            enumero = snumero.substr(1, pdecimal - 1);
        }
        edecimal = snumero.substr(pdecimal, snumero.length);
        console.log("--> " + enumero);
    }
    var longitud = enumero.length;
    for (pos = longitud - 1; pos >= 0; pos--) {
        var cnumero = enumero.charAt(pos);
        fnumero = cnumero + fnumero;
        if ((longitud - pos) !== longitud) {
            if ((longitud - pos) % 3 === 0) {
                fnumero = separador_miles + fnumero;
            }
        }
    }
    fnumero = esigno + fnumero + edecimal;
    return fnumero;
}

function dar_formato_hora(hora) {
    var fhora = "";
    var shora = hora.toString().replace(/\:/g, "");
    var longitud = shora.length - 1;
    if (longitud > 3) {
        longitud = 3;
    }
    for (pos = longitud; pos >= 0; pos--) {
        var chora = shora.charAt(pos);
        fhora = chora + fhora;
        if (pos === 2) {
            fhora = ":" + fhora;
        }
    }
    return fhora;
}

function formatearNumero(id) {
    var tecla = event.which;
    if (tecla !== 37 && tecla !== 38 && tecla !== 39 && tecla !== 40 && tecla !== 9) {
        var monto = $(id).val();
        $(id).val(dar_formato_numero(monto, ",", "."));
    }
}




