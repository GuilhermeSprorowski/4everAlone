<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
        
        <script>            
            function expandirEncontro(id) {
                $("#expand-encontro-" + id).toggleClass("is-active");
            } 
        </script>

    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>

        <div class="container margem">
            <h1 class="notification is-info">Pessoas que possam te interessar</h1>
            <div class="flex-33">
                <c:forEach items="${clienteList}" var="pessoa">
                    <div class="encontro flex-column">
                        <img src="ImageServlet?action=view&img=${pessoa.clienteId}">
                        <p class="title">
                          ${pessoa.nome}
                        </p>
                      <div class="descricao">
                          ${pessoa.descricao}
                      </div>    
                      <footer class="card-footer">
                        <a href="EncontroServlet?action=solicitar-encontro-new&clienteId=${pessoa.clienteId}" class="card-footer-item">Solicitar Encontro</a>
                        <a onClick="expandirEncontro(${pessoa.clienteId})" class="card-footer-item">Mais informações</a>
                      </footer>
                    </div>
                      
                      <div id="expand-encontro-${pessoa.clienteId}" class="modal">
                            <div class="modal-background"></div>
                            <div class="modal-content encontro-details">
                                <img src="ImageServlet?action=view&img=${pessoa.clienteId}" alt="">
                                <div class="encontro">
                                    <div>
                                        <p class="title">
                                            ${pessoa.nome}
                                        </p>
                                        <p>
                                            ${pessoa.descricao}
                                        </p>
                                        <hr>
                                        <p><strong>Idade:</strong> ${pessoa.idade} anos</p>
                                        <p><strong>Altura:</strong> ${pessoa.altura}cm</p>
                                        <p><strong>Cor do cabelo:</strong> ${pessoa.corCabelo.cor}</p>
                                        <p><strong>Cor da pele:</strong> ${pessoa.corPele.cor}</p>
                                        <p><strong>Escolaridade:</strong> ${pessoa.escolaridade.descricao}</p>
                                    </div>
                                </div>
                            </div>
                            <button onClick="expandirEncontro(${pessoa.clienteId})" class="modal-close is-large" aria-label="close"></button>

                        </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
