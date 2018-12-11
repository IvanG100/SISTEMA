function agregarCliente() {
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
            $("#id_cliente").focus();
            $("#id_cliente").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_cliente").focus();
        }
    });
}

function modificarCliente() {
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
            $("#id_cliente").focus();
            $("#id_cliente").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarCliente() {
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
            $("#id_cliente").focus();
            $("#id_cliente").select();
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
            var ci = dar_formato_numero(json.ci_cliente, ",", ".");
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#ci_cliente").val(ci);
            $("#direccion_cliente").val(json.direccion_cliente);
            $("#telefono_cliente").val(json.telefono_cliente);
            $("#correo_cliente").val(json.correo_cliente);
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
}

function buscarNombreCliente() {
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
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
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
function limpiarFormulario() {
    $("#id_cliente").val("");
    $("#nombre_cliente").val("");
    $("#apellido_cliente").val("");
    $("#ruc_cliente").val("");
    $("#ci_cliente").val("");
    $("#direccion_cliente").val("");
    $("#telefono_cliente").val("");
    $("#correo_cliente").val("");
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacio.");
        $("#nombre_cliente").focus();
    }
    if ($("#apellido_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Apellido no puede estar vacio.");
        $("#apellido_cliente").focus();
    }
    
    if ($("#ruc_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Ruc no puede estar vacio.");
        $("#ruc_cliente").focus();
    }
    
    if ($("#ci_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Cedula no puede estar vacio.");
        $("#ci_cliente").focus();
    }
    
    if ($("#direccion_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Direccion no puede estar vacio.");
        $("#direccion_cliente").focus();
    }
    
    if ($("#telefono_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Telefono no puede estar vacio.");
        $("#telefono_cliente").focus();
    }
    
    if ($("#correo_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Correo no puede estar vacio.");
        $("#correo_cliente").focus();
    }

    return valor;
}

function buscarCiCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCi.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            if (json.nuevo === "false"){
                 $("#ci_cliente").val("");
                  $("#ci_cliente").focus();
            }else{
                 
            }
            
            /*if (json.nuevo === "true") {
                
            } else {
                alert("Datos Repetidos");
                $("#ci_cliente").val("");
                $("#ci_cliente").focus();
            }*/
            
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

function buscarTelefonoCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarTelefono.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            if (json.nuevo === "false"){
                 $("#telefono_cliente").val("");
                  $("#telefono_cliente").focus();
            }else{
                 
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

function buscarRucCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarRuc.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            if (json.nuevo === "false"){
                 $("#ruc_cliente").val("");
                  $("#ruc_cliente").focus();
            }else{
                 
            }
            
            /*if (json.nuevo === "true") {
                
            } else {
                alert("Datos Repetidos");
                $("#ci_cliente").val("");
                $("#ci_cliente").focus();
            }*/
            
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

function validarCorreo(elemento){

  var texto = document.getElementById(elemento.id).value;
  var regex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
  
  if (!regex.test(texto)) {
      document.getElementById("resultado").innerHTML = "Correo invalido";
      $("#correo_cliente").val("");
      $("#correo_cliente").focus();
  } else {
    document.getElementById("resultado").innerHTML = "";
  }
//<div class="col-md-11">
    //<input id="correo_proveedor" name="correo_proveedor" type="email" class="form-control input-sm" placeholder="Correo" onkeypress="validarCorreo(this)">
       //<a id='resultado'></a>
       //</div>
}

