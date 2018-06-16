<%-- 
    Document   : relatorios
    Created on : 16/06/2018, 11:57:04
    Author     : MURILO
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $("#dataComeco").datepicker({dateFormat: 'dd/mm/yy',
                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'],
                    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
                });
                $("#dataFinal").datepicker({dateFormat: 'dd/mm/yy',
                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'],
                    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
                });
            });
        </script>
    </head>
    <body>
        <form action="GeradorRelatorio" method="POST">
            <select name="acao">
                <option value="1">Relatório Gerencial de Encontros</option>
                <option value="2">Relatório Gerencial de Encontros por Pessoa</option>
                <option value="3">Relatório Gerencial de Festas</option>
                <option value="4">Relatório Gerencial de Festas por Pessoa</option>
                <option value="5">Relatório Gerencial de Produtividade</option>
            </select>
            <select name="idUsuario">
                <c:forEach items="${clienteList}" var="objeto">
                    <option value="${objeto.clienteId}">${objeto.nome},${objeto.cpf}</option>
                </c:forEach>  
            </select>
            <input type="date"   name="dataComeco" class="form-control" id="dataComeco" value="">
            <input type="date"   name="dataFinal" class="form-control" id="dataFinal" value="">
            <input type="submit"  value="Salvar" name="submit">
        </form>
    </body>
    <script>
        var $dropdown1 = $("select[name='acao']");

        $dropdown1.change(function () {
            var selectedItem = $($dropdown1).val();
            if (selectedItem == "1" || selectedItem == "3") {
                $('select[name="idUsuario"]').hide();
            } else if (selectedItem == "5") {
                $('select[name="idUsuario"]').hide();
                $('input[name="dataComeco"]').hide();
                $('input[name="dataFinal"]').hide();
            } else {
                $('select[name="idUsuario"]').show();
                $('input[name="dataComeco"]').show();
                $('input[name="dataFinal"]').show();
            }
        });
    </script>
</html>
