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
        <title>Relatórios</title>
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">


        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>

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
        <c:import url="../components/header.jsp" >
        </c:import>

        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">
                        Relatórios Gerenciais
                    </h1>
                    <h2 class="subtitle">
                        Selecione e visualize um relatório
                    </h2>

                </div>
            </div>
        </section>
        <div class="container margem">
            <form action="GeradorRelatorio" method="POST">
                <div class="field">
                    <label class="label" for="acao">Relatório</label>
                    <div class="select">
                        <select name="acao">
                            <option value="1">Relatório Gerencial de Encontros</option>
                            <option value="2">Relatório Gerencial de Encontros por Pessoa</option>
                            <option value="3">Relatório Gerencial de Festas</option>
                            <option value="4">Relatório Gerencial de Festas por Pessoa</option>
                            <option value="5">Relatório Gerencial de Produtividade</option>
                        </select>
                    </div>
                </div>
                <div id="usuario" class="field">
                    <label class="label" for="idUsuario">Usuário</label>
                    <div class="select">
                        <select name="idUsuario">
                            <c:forEach items="${clienteList}" var="objeto">
                                <option value="${objeto.clienteId}">${objeto.nome},${objeto.cpf}</option>
                            </c:forEach>  
                        </select>
                    </div>
                </div>
                <div id="dtInicio" class="field">
                    <label class="label">Data início:</label>
                    <div class="control has-icons-left">
                        <input name="dataComeco" class="input" type="text" id="dataComeco"
                               placeholder="Data inicio">
                        <span class="icon is-left">
                            <i class="fas fa-calendar-alt"></i>
                        </span> 
                    </div>
                </div>
                <div id="dtFim" class="field">
                    <label class="label">Data final:</label>
                    <div class="control has-icons-left">
                        <input name="dataFinal" class="input" type="text" id="dataFinal"
                               placeholder="Data final">
                        <span class="icon is-left">
                            <i class="fas fa-calendar-alt"></i>
                        </span> 
                    </div>
                </div>
                <div class="field is-grouped">
                    <div class="control">
                        <button name="submit" type="submit" class="button is-link">Visualizar</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script>
        $(function() {
            var $dropdown1 = $("select[name='acao']");
            function selectCurrent() {
                var selectedItem = $($dropdown1).val();
                if (selectedItem == "1" || selectedItem == "3") {
                    $('#usuario').hide();
                } else if (selectedItem == "5") {
                    $('#usuario').hide();
                    $('#dtInicio').hide();
                    $('#dtFim').hide();
                } else {
                    $('#usuario').show();
                    $('#dtInicio').show();
                    $('#dtFim').show();
                }
            }
            $dropdown1.change(selectCurrent);
            selectCurrent();
        }); 
    </script>
</html>
