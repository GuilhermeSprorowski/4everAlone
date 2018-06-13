<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Clientes</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>

    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>
            <section class="hero is-info">
                <div class="hero-body">
                    <div class="container">
                        <h1 class="title">
                        <c:choose>
                            <c:when test="${cliente == null}">
                                Novo Cliente
                            </c:when>
                            <c:otherwise>
                                Editar Cliente
                            </c:otherwise>
                        </c:choose> 
                    </h1>
                    <h2 class="subtitle">
                        Administração de Clientes
                    </h2>
                </div>
            </div>
        </section>
        <div class="container margem">            
            <form method="POST" action="ClienteServlet?action=salva" class="column card">               
                <div class="columns">
                    <div class="column">
                        <div class="columns">
                            <div class="field column">
                                <label class="label">Data de nascimento:</label>
                                <div class="control has-icons-left">
                                    <input name="dataNasc" class="input" type="text" id="datepicker"
                                           placeholder="Data nascimento" value="${cliente.dataNascS}">
                                    <span class="icon is-left">
                                        <i class="fas fa-calendar-alt"></i>
                                    </span> 
                                </div>
                            </div>
                            <div class="field column">
                                <label class="label">Altura (em cm):</label>
                                <div class="control">
                                    <input name="altura" class="input" type="text"
                                           placeholder="Altura (em cm)" value="${cliente.altura}">
                                </div>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column field">
                                <label class="label" for="escolaridade">Escolaridade</label>
                                <div class="select">
                                    <select name="escolaridade">
                                        <c:forEach items="${escolaridade}" var="es">
                                            <option value="${es.idEscolaridade}"
                                                    <c:if test="${form == 'alterar' && cliente.escolaridade == es.descricao}">selected</c:if>
                                                    >${es.descricao}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="column field">
                                <label class="label" for="corPele">Cor da Pele</label>
                                <div class="select">
                                    <select name="corPele">
                                        <c:forEach items="${corPele}" var="cp">
                                            <option value="${cp.idCorPele}"
                                                    <c:if test="${cliente.corPele.idCorPele == cp.idCorPele}">selected</c:if>
                                                    >${cp.cor}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="column field">
                                <label class="label" for="corCabelo">Cor do Cabelo</label>
                                <div class="select">
                                    <select name="corCabelo">
                                        <c:forEach items="${corCabelo}" var="cc">
                                            <option value="${cc.idCorCabelo}"  
                                                    <c:if test="${cliente.corCabelo.idCorCabelo == cc.idCorCabelo}">selected</c:if>
                                                    >${cc.cor}</option>
                                        </c:forEach>
                                    </select>
                                </div>
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

                        <div class="field">
                            <label class="label" for="rua">Endereço:</label>
                            <input class="input" type="text" name="rua" value="${cliente.endereco.rua}"/>
                        </div>

                        <div class="field">
                            <label class="label" for="descricao">Descrição:</label>
                            <textarea rows="3" class="textarea" type="text" name="descricao">${cliente.descricao}</textarea>
                        </div>
                    </div>

                </div>
                <div class="field is-grouped">
                    <div class="control">
                        <button type="submit" class="button is-link">Salvar</button>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>
