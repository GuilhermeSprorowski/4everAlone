$(function() {
    $('#datepicker').datepicker($.extend({
        showMonthAfterYear: false,
        dateFormat: 'dd/mm/yy'
      }
    ));
    $('#horario').mask("00:00");
    $('#salario').mask("000.000.000.00", {reverse: true});
});