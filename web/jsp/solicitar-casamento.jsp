<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitar Casamento</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>
        <script src="js/forms.js"></script>
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>
            <form action="CasamentoServlet?action=orcar" method="POST">
                <section class="hero is-info">
                    <div class="hero-body">
                        <div class="container">
                            <h1 class="title">
                                Escolha a pessoa com quem pretende se casar
                            </h1>
                            <div class="field">
                                <div class="select">
                                    <select name="conjugeId">
                                    <c:forEach var="c" items="${encontros}">
                                        <option value="${c.solicitado.clienteId}">${c.solicitado.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <div class="container margem">

                <div class="columns">
                    <div class="field column">
                        <label class="label">Data do casamento</label>
                        <div class="control has-icons-left">
                            <input name="dataCasamento" class="input" type="text" id="datepicker"
                                   placeholder="Selecione a data do casamento">
                            <span class="icon is-left">
                                <i class="fas fa-calendar-alt"></i>
                            </span> 
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Horário do casamento:</label>
                        <div class="control has-icons-left">
                            <input name="horario" class="input" type="text" id="horario"
                                   placeholder="00:00">
                            <span class="icon is-left">
                                <i class="fas fa-clock"></i>
                            </span> 
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Numero de convidados:</label>
                        <div class="control">
                            <input name="convidados" class="input" type="text"
                                   placeholder="Número de convidados">
                        </div>
                    </div>
                </div>
                <div class="columns">
                    <div class="field column">
                        <label class="label">Padrinho:</label>
                        <div class="control">
                            <input name="padrinho1" class="input" type="text"
                                   placeholder="Nome do padrinho">
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Madrinha:</label>
                        <div class="control">
                            <input name="madrinha1" class="input" type="text"
                                   placeholder="Nome da madrinha">
                        </div>
                    </div>
                </div>
                <div class="columns">
                    <div class="field column">
                        <label class="label">Padrinho:</label>
                        <div class="control">
                            <input name="padrinho2" class="input" type="text"
                                   placeholder="Nome do segundo padrinho">
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Madrinha:</label>
                        <div class="control">
                            <input name="madrinha2" class="input" type="text"
                                   placeholder="Nome da segunda madrinha">
                        </div>
                    </div>
                </div>
                <div class="columns">
                    <div class="field column">
                        <label class="label">Padre/pastor:</label>
                        <div class="control">
                            <input name="padre" class="input" type="text"
                                   placeholder="Nome do padre">
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Igreja de preferência:</label>
                        <div class="control">
                            <input name="igreja" class="input" type="text"
                                   placeholder="Nome da igreja">
                        </div>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Local da lua de mel de preferência:</label>
                    <div class="control">
                        <input name="lua" class="input" type="text"
                               placeholder="Nome do local">
                    </div>
                </div>
                <button class="button is-info">Solicitar</button>
            </div>
        </form>

    </body>
</html>
