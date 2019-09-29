$(document).ready(function(){
  var inicio=$('input[name="j_idt9:inicio"]'); 
  inicio.datepicker({
    format: 'dd/mm/yyyy',
    todayHighlight: true,
    autoclose: true
  });
  
  var final=$('input[name="j_idt9:final"]'); 
    final.datepicker({
    format: 'dd/mm/yyyy',
    todayHighlight: true,
    autoclose: true
  });
});