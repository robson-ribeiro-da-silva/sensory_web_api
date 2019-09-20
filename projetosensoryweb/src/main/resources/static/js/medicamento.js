/**
 *
 */
var res = new ResponseController;


$(document).ready(function () {
   $("#select-medicamento").hide();
});

async function response(length, value){
    return await res.$JSONLike('http://localhost:8080/fileJson/medicamentoJson', length, value)
}

$("#input-medicamento").keyup(function (event) {
    let str = $(this).val().toLocaleUpperCase();
    let medicamentos = response($(this).val().length, str);
    medicamentos.then(data=>{
        console.log(data);
        res.creatSelectMedicineHTML('', "select-medicamento", data);
    });
});

$("#select-medicamento").click(function (event) {
    if(($(this).val() != null) && ($(this).val() != "")){
        res.createTableHTML(event.originalEvent.path[0].innerText, 'tbody-medicamentos', $(this).val());
    }
});

$(document).click(function (event) {
    let objeto = event.target;
    if(objeto.getAttribute("class") == "btn btn-danger" && !$("#select-procedimentos").length){
        let result = confirm("Deseja Remover ?");
        if(result){
            let objeto = event.target;
            res.deleteTableHTML(objeto, 'tbody-medicamentos', 'btn btn-danger', event);
        }else{
            event.preventDefault();
        }
    }
});
