$(document).ready(function () {
    $("title").html("Tu mejor compra");
//            $("#panel-lateral").panel("open");

    $("#btn-toggle-form-buscar").click(function () {
        $("#cont-form-buscar").fadeToggle();
    });

    $(".producto-reciente h2").dotdotdot({ height: 32 });
});

function mostrarClaves() {
    PF('miGrowl').show([
        {summary: 'andres = 123', detail: '', severity: 'info'},
        {summary: 'carlos = 456', detail: '', severity: 'info'},
        {summary: 'fernado = 789', detail: '', severity: 'info'}
    ]);
}
