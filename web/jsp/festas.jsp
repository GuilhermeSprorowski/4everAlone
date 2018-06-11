<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos</title>
        
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
                  Eventos perto de você
                </h1>
              </div>
            </div>
        </section>
        <div class="container margem">
            <c:forEach items="${convites}" var="convite">
                <div class="list-item">
                    <div class="flex-row padding-bottom">                       
                        <div class="descricao">
                            <h1 class="title">${convite.festa.tema}</h1>
                            <p>
                                ${convite.festa.descricao}
                            </p>
                        </div>
                        <div class="descricao">
                            <p>
                                <strong>Dia:</strong> <fmt:formatDate type="date" value="${convite.festa.datahora}"></fmt:formatDate>
                            </p>
                            <p>
                                <strong>Horário:</strong> <fmt:formatDate type="time" value="${convite.festa.datahora}"></fmt:formatDate>
                            </p>
                            <hr>
                            <p>
                                ${convite.festa.enderecoString}
                            </p>
                        </div>
                    </div>

                    <c:if test="${!convite.confirmado and convite.dataResposta == null}">
                        <footer class="card-footer">
                            <a href="FestaServlet?action=update&resp=true&conviteId=${convite.idConvite}" class="card-footer-item">Confirmar presença</a>
                            <a href="FestaServlet?action=update&resp=false&conviteId=${convite.idConvite}" class="card-footer-item">Não comparecerei</a>
                        </footer>
                    </c:if>
                    <c:if test="${convite.confirmado}">
                        <center><strong>Presença confirmada</strong></center>
                    </c:if>
                    <c:if test="${!convite.confirmado and convite.dataResposta != null}">
                        <center><strong>Presença rejeitada</strong></center>
                    </c:if>
                </div>
            </c:forEach>

        </div>
    </body>
</html>
