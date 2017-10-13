$( document ).ready(function() {

    var url = window.location;

    $("#btnId").click(function(event){
        event.preventDefault();
        // Open Bootstrap Modal
        openModel();
    })

    // Open Bootstrap Modal
    function openModel(){
        $("#modalId").modal();
    }
})