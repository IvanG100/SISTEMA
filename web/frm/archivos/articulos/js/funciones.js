function agregarArticulo() {
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
            $("#id_articulo").focus();
            $("#id_articulo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_articulo").focus();
        }
    });
}

function modificarArticulo() {
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
            $("#id_articulo").focus();
            $("#id_articulo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            
        }
    });
}

function eliminarArticulo() {
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
            $("#id_articulo").focus();
            $("#id_articulo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
                
            }
        }
    });
}

function buscarIdArticulo() {
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
            var venta = dar_formato_numero(json.precio_venta, ",", ".");
            var compra = dar_formato_numero(json.precio_compra, ",", ".");
            
            $("#id_articulo").val(json.id_articulo);
            $("#nombre_articulo").val(json.nombre_articulo);
            $("#descripcion_articulo").val(json.descripcion_articulo);
            //$("#precio_unitario").val(json.precio_unitario);
            $("#precio_venta").val(venta);
            $("#precio_compra").val(compra);
            //$("#iva_5").val(json.iva_5);
            
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            $("#id_categoria").val(json.id_categoria);
            $("#nombre_categoria").val(json.nombre_categoria);
            $("#codigo_articulo").val(json.codigo_articulo);
            $("#iva_articulo").val(json.iva_articulo);
            $("#id_ubicacion").val(json.id_ubicacion);
            $("#nombre_ubicacion").val(json.nombre_ubicacion);
          
            
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_articulo", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_articulo", "#botonModificar", true);
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

function buscarNombreArticulo() {
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
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_articulo").val(id);
              $("#nombre_articulo").focus();
              buscarIdArticulo();
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

function buscarIdMarca() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreMarca() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreMarca.jsp',
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
              $("#id_marca").val(id);
              $("#nombre_marca").focus();
              buscarIdMarca();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarIdCategoria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCategoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_categoria").val(json.id_categoria);
            $("#nombre_categoria").val(json.nombre_categoria);
            
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

function buscarNombreCategoria() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreCategoria.jsp',
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
              $("#id_categoria").val(id);
              $("#nombre_categoria").focus();
              buscarIdCategoria();
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

function buscarCodigoArticulo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCodigo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            if (json.nuevo === "false"){
                 $("#codigo_articulo").val("");
                  $("#codigo_articulo").focus();
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

function buscarIdUbicacion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdUbicacion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ubicacion").val(json.id_ubicacion);
            $("#nombre_ubicacion").val(json.nombre_ubicacion);
            
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

function buscarNombreUbicacion() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreUbicacion.jsp',
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
              $("#id_ubicacion").val(id);
              $("#nombre_ubicacion").focus();
              buscarIdUbicacion();
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

function buscarUbicacion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarUbicacion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            
            if (json.nuevo === "false") {
               $("#nombre_ubicacion").val("");
               $("#nombre_ubicacion").focus();
           } else {
               
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

function limpiarFormulario() {
    $("#id_articulo").val("");
    $("#nombre_articulo").val("");
    $("#descripcion_articulo").val("");
     //$("#precio_unitario").val(json.precio_unitario);
    $("#precio_venta").val("");
    $("#precio_compra").val("");
    //$("#iva_5").val("");
    
    $("#id_marca").val("");
    $("#nombre_marca").val("");
    $("#id_categoria").val("");
    $("#nombre_categoria").val("");
    $("#codigo_articulo").val("");
    $("#id_ubicacion").val("");
    $("#nombre_ubicacion").val("");
    
}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_articulo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacio.");
        $("#nombre_articulo").focus();
    }
    
    if ($("#descripcion_articulo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Descripcion no puede estar vacio.");
        $("#descripcion_articulo").focus();
    }
    
    if ($("#precio_venta").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Precio Venta no puede estar vacio.");
        $("#precio_venta").focus();
    }
    
    
    if ($("#precio_compra").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Precio Compra no puede estar vacio.");
        $("#precio_compra").focus();
    }
    
    /*if ($("#iva_5").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Iva 5% no puede estar vacio.");
        $("#iva_5").focus();
    }*/
    
    
    
    if ($("#codigo_articulo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Codigo no puede estar vacio.");
        $("#codigo_articulo").focus();
    }
    
    if ($("#id_marca").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Marca no puede estar vacio.");
        $("#id_marca").focus();
    }
    
    if ($("#id_categoria").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Categoria no puede estar vacio.");
        $("#id_categoria").focus();
    }
    
    if ($("#id_ubicacion").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Ubicacion no puede estar vacio.");
        $("#id_ubicacion").focus();
    }


    return valor;
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


