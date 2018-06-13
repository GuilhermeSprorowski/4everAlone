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
        
        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">Bem vindo(a), ${login.nome}</h1>
                    <h2 class="subtitle">Eventos gerais</h2>
                </div>
            </div>
        </section>
        
        <div class="container margem">
            <c:if test="${festaList.isEmpty()}"> <h1>Nenhuma Festa Registrada<h1></c:if>
            <c:forEach items="${festaList}" var="festa">
                <div class="card">
                  <header class="card-header">
                    <p class="card-header-title title">
                      ${festa.tema}
                    </p>
                  </header>
                    <footer style="margin-left: 15px; margin-right: 15px;" class="card-footer flex-row">
                        <p><strong><i class="fas fa-calendar-alt"></i></strong> <fmt:formatDate type="date" value="${festa.datahora}"/></p>
                        <p><strong><i class="fas fa-clock"></i></strong> <fmt:formatDate type="time" value="${festa.datahora}"/></p>
                        <p><strong><i class="fas fa-map-marker"></i></strong> ${festa.endereco.nomeLocal}</p>
                    </footer>
                  <div class="card-content card-content-limit">
                    <div class="content">
                      ${festa.descricao}
                    </div>
                    
                  </div>
                    
                </div>
            </c:forEach>
        </div>

    </body>
</html>
