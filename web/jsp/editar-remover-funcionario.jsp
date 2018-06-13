<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Funcionarios</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">


        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="js/forms.js"></script>

        <script>
            $(document).ready(function () {
                $("#estado").change(function () {
                    getCidades();
                });

            <c:if test = "${form == 'alterar'}">getCidades()</c:if>
                });

                function getCidades() {
                    var estadoId = $("#estado").val();
                    var url = "AJAXServlet?action=viewCidade";
                    $.ajax({
                        url: url, // URL da sua Servlet
                        data: {
                            estadoId: estadoId
                        }, // ParÃ¢metro passado para a Servlet
                        dataType: 'json',
                        success: function (data) {
                            $("#cidade").empty();
                            $.each(data, function (i, obj) {
                                var cidadeId =
            <c:if test = "${form == 'alterar'}">${cliente.endereco.cidade.idCidade}</c:if>
            <c:if test = "${form != 'alterar'}">null</c:if>;
                                var appendHtml = '<option ' +
                                        (cidadeId === obj.idCidade ? 'selected' : '')
                                        + ' value=' + obj.idCidade + '>' + obj.nome + '</option>';
                                $("#cidade").append(appendHtml);
                            });
                        },
                        error: function (request, textStatus, errorThrown) {
                            alert(request.status + ', Error: ' + request.statusText);
                        }
                    });
                }
            </script>
        </head>
        <body>
        <c:import url="../components/header.jsp" ></c:import>
            <section class="hero is-info">
                <div class="hero-body">
                    <div class="container">
                        <form method="POST" action="ImageServlet?action=salva&img=${cliente.clienteId}" enctype='multipart/form-data'>
                            <div class="columns">
                                <div class="column searchfuncio">
                                <div class="select">
                                    <select id="funcio" name="funcio">
                                        <option value="func">Nome Funcionario</option>
                                    </select>
                                </div>

                            <button type="submit" class="button is-success">Buscar</button>
                            </div>
                            </div>
                    </form>

                </div>
            </div>
        </section>
        <div class="container margem">
                <div>
                        <img style="width: 256px    " src="ImageServlet?action=view&img=${cliente.clienteId}"/><br>
                        <input type="file" class="button is-link" name="file" id="foto"/>
                        <input type="submit" class="button is-link" value="Enviar Foto" />
                    </div>
        </div>
        <div class="container margem">            
            <form method="POST" action="ClienteServlet?action=salva" class="column card">               
                <div class="columns">
                    <div class="column">
                        <div class="columns">
                            <div class="field column">
                                <label class="label">Nome completo</label>
                                <div class="control">
                                    <input name="altura" class="input" type="text"
                                           placeholder="Nome Completo" value="${cliente.altura}">
                                </div>
                            </div>
                            <div class="field column is-3">
                                <label class="label">Data de nascimento:</label>
                                <div class="control has-icons-left">
                                    <input name="dataNasc" class="input" type="text" id="datepicker"
                                           placeholder="Data nascimento" value="${cliente.dataNascS}">
                                    <span class="icon is-left">
                                        <i class="fas fa-calendar-alt"></i>
                                    </span> 
                                </div>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <label class="label" for="rua">EndereÃ§o:</label>
                                <input name="altura" class="input" type="text"
                                       placeholder="Rua, nÃºmero" value="${cliente.altura}">
                            </div>
                            <div class="column is-3">
                                <label class="label" for="rua">Bairro</label>
                                <input name="altura" class="input" type="text"
                                       placeholder="Bairro" value="${cliente.altura}">
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <div class="field">
                                    <label class="label" for="idEstado">Estado</label>
                                    <div class="select">
                                        <select id="estado" name="idEstado">
                                            <c:forEach items="${estados}" var="es">
                                                <option value="${es.idEstado}"
                                                        <c:if test="${form == 'alterar' && cliente.endereco.estado.idEstado == es.idEstado}">selected</c:if>
                                                        >(${es.sigla}) ${es.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="idEndereco" value="${cliente.endereco.enderecoId}"/>
                            <div class="column">
                                <div class="field">
                                    <label class="label" for="cidadeId">Cidade</label>
                                    <div class="select">
                                        <select id="cidade" name="idCidade">
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="columns">
                            <div class="column">
                                <label class="label" for="rua">CPF</label>
                                <input name="altura" class="input" type="text"
                                       placeholder="000.000.000-00" value="${cliente.altura}">
                            </div>
                            <div class="column">
                                <label class="label" for="rua">RG</label>
                                <input name="altura" class="input" type="text"
                                       placeholder="00.000.000-0" value="${cliente.altura}">
                            </div>
                            <div class="column">
                                <label class="label" for="rua">RemuneraÃ§Ã£o</label>
                                <input name="altura" class="input" type="text"
                                       placeholder="R$0,00" value="${cliente.altura}">
                            </div>
                        </div>
                    </div>
                </div>


                        <div class="columns">
                            <div class="column is-offset-5">
                        <button type="submit" class="button is-link">Salvar</button>
                        <button type="submit" class="button is-danger">Excluir</button>
                            </div>
                        </div>

            </form>
        </div>
    </body>
</html>
