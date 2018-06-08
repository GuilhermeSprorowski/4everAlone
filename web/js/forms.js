$(function() {
    $('#datepicker').datepicker($.extend({
        showMonthAfterYear: false,
        dateFormat: 'dd/mm/yy'
      }
    ));
    $('#horario').mask("00:00");
});