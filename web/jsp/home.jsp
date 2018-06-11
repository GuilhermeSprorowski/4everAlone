<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>

        <div class="container margem">
            <div class="columns is-2">
                <div class="column card">
                    <h1 class="notification is-info">Convites de eventos
                    <c:if test="${empty conviteList}">
                       - Nenhum convite pendente
                    </c:if></h1>
                    <c:forEach items="${conviteList}" var="convite">
                        <div class="card">
                          <header class="card-header">
                            <p class="card-header-title title">
                              ${convite.festa.tema}
                            </p>
                          </header>
                          <div class="card-content card-content-limit">
                            <div class="content">
                              ${convite.festa.descricao}
                            </div>
                          </div>
                          <footer class="card-footer">
                            <a href="#" class="card-footer-item">Aceitar</a>
                            <a href="#" class="card-footer-item">Recusar</a>
                          </footer>
                        </div>
                    </c:forEach>
                </div>
                <div class="column card">
                    <h1 class="notification is-success" >Encontros Pendentes 
                    <c:if test="${empty encontroList}">
                       - Nenhum encontro pendente
                    </c:if>
                    </h1>
                    <c:forEach items="${encontroList}" var="encontro">
                        <div class="columns">
                            <div class="column">
                                <img src="ImageServlet?action=view&img=${encontro.solicitado.clienteId}"/>
                            </div>
                            <div class="column">
                                <h1 class="title">${encontro.solicitado.nome}</h1>
                                <p>${encontro.solicitado.descricao}</p>
                            </div>

                        </div>
                        <footer class="card-footer flex-row">
                            <p><strong><i class="fas fa-calendar-alt"></i></strong> <fmt:formatDate type="date" value="${encontro.dataEncontro}"/></p>
                            <p><strong><i class="fas fa-clock"></i></strong> <fmt:formatDate type="time" value="${encontro.dataEncontro}"/></p>
                            <p><strong><i class="fas fa-map-marker"></i></strong> ${encontro.endereco.nomeLocal}</p>
                        </footer>
                    </c:forEach>
                    
                </div>

            </div>

        </div>

    </body>
</html>
