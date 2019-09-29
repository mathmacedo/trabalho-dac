$(document).ready(function(){
  $("[id*='inicio']").datepicker({
    format: 'dd/mm/yyyy',
    todayHighlight: true,
    autoclose: true
  });
  
  $("[id*='final']").datepicker({
    format: 'dd/mm/yyyy',
    todayHighlight: true,
    autoclose: true
  });
});