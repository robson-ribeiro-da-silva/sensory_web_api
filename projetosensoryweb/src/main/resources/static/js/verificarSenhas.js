$("#nova-senha-confirmar").keyup(function () {
   if($(this).val() != $("#nova-senha").val()){
       $("#alerts").html("<div class='alert alert-danger'> " +
           "<span>Senhas não são iguais!</span>" +
           "</div>");
   } else{
       $("#alerts").html("");
   }
});

$("#nova-senha").keyup(function () {
    $("#alerts").html("");
});


$("#submit-nova-senha").click(function (event) {
    if($("#nova-senha-confirmar").val() != "" && $("#nova-senha").val() != "" && $("#nova-senha-confirmar").val() == $("#nova-senha").val()){
        $(this).submit();
    }else{
        event.preventDefault();
        if($("#nova-senha-confirmar").val() == "" && $("#nova-senha").val() == ""){
            $("#alerts").html("<div class='alert alert-danger'> " +
                "<span>Campos obrigatórios!</span>" +
                "</div>");
        }else if($("#nova-senha-confirmar").val() != $("#nova-senha").val()){
            $("#alerts").html("<div class='alert alert-danger'> " +
                "<span>Senhas não são iguais!</span>" +
                "</div>");
        } else{
            $("#alerts").html("");
        }
    }
});