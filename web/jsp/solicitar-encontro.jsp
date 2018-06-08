<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitar Encontro</title>
        
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>

        <div class="container margem">
            <h1 class="notification is-info">Pessoas que possam te interessar</h1>
            <div class="flex-33">
                <c:forEach items="encontros" var="pessoa">
                    <div class="encontro flex-column">
                        <img src="ImageServlet?action=view&img=${pessoa.id}">
                        <p class="title">
                          ${pessoa.nome}
                        </p>
                      <div class="descricao">
                          ${pessoa.descricao}
                      </div>    
                      <footer class="card-footer">
                        <a href="#" class="card-footer-item">Solicitar Encontro</a>
                        <a href="#" class="card-footer-item">Mais informações</a>
                      </footer>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
