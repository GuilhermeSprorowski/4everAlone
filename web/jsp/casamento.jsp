<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Casamento</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>

    </head>
    <body>
        <c:import url="../components/header.jsp" >

        </c:import>

        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">
                        Se apaixonou e deseja se casar com a pessoa?
                    </h1>
                    <h2 class="subtitle">
                        Então planeje seu casamento conosco!
                    </h2>
                    <button class="button"><a href="CasamentoServlet?action=solicitar">Solicitar orçamento</a></button>
                </div>
            </div>
        </section>
        <div class="container margem">
            <h1 class="title">Orçamento solicitado</h1>
            <c:if test="${pedidosList[0].itensOrcamento == null}"> 
                <p>Aguardando resposta do seu orçamento solicitado.</p>
            </c:if>
            <c:if test="${empty pedidosList}">
                <p>Nenhum orçamento feito.</p>
            </c:if>
            <c:set var="newLine" value="\n"/>
            <c:forEach items="${pedidosList}" var="pedido">
                <c:if test="${not empty pedido.itensOrcamento}">                          
                    <div class="card">
                        <table width="100%" class="table">
                            <thead> 
                                <tr>
                                    <th>Descrição</th>
                                </tr>
                            </thead>
                            <tfoot> 
                                <tr>
                                    <th>Valor total: R$ <fmt:formatNumber value="${pedido.vlrTotal}" minFractionDigits="2"/></th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <tr>
                                    <th>${fn:replace(pedido.itensOrcamento, newLine, "<br>")}</th>
                                </tr>
                            </tbody>

                        </table>
                        <footer class="card-footer">
                            <a href="CasamentoServlet?action=resp&aceio=true&idPedido=${pedido.idPedido}" class="card-footer-item">Aceitar orçamento</a>
                            <a href="CasamentoServlet?action=resp&aceio=false&idPedido=${pedido.idPedido}" class="card-footer-item">Recusar</a>
                        </footer>
                    </div>
                    <hr>
                    </c:if>
                </c:forEach>
        </div>
    </body>
</html>
