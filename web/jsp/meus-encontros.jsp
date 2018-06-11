<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meus Encontros</title>
        
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
            <div class="columns is-2">
                <div class="column">
                    <h1 class="notification is-info">Solicitações enviadas</h1>
                    <c:forEach items="${encontrosEnviados}" var="encontro">
                        <div class="media">
                            <figure class="media-left">
                              <p class="image is-64x64">
                                <img src="ImageServlet?action=view&img=${encontro.solicitado.clienteId}">
                              </p>
                            </figure>
                            <div class="media-content">
                              <div class="content">
                                <p>
                                  <strong>${encontro.solicitado.nome}</strong>
                                  <br>
                                  ${encontro.solicitado.descricao}
                                </p>
                              </div>
                              <nav class="level is-mobile">
                                <div class="level-left">
                                    <strong>
                                        <c:if test="${encontro.aceito}">Encontro aceito</c:if>
                                        <c:if test="${!encontro.aceito and encontro.dataResposta == null}">Aguardando resposta</c:if> 
                                        <c:if test="${!encontro.aceito and encontro.dataResposta != null}">Recusado</c:if>    
                                    </strong>
                                </div>
                              </nav>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="column">
                    <h1 class="notification is-success" >Solicitações recebidas</h1>
                    <c:forEach items="${encontrosParaResponder}" var="enc">
                        <div class="encontro flex-column">
                            <p class="title">
                              ${enc.solicitado.nome}
                            </p>
                          <div class="descricao">
                            ${enc.solicitado.descricao}
                          </div>    
                          <footer class="card-footer">
                            <a href="EncontroServlet?action=resposta-encontro&resp=true&encontroId=${enc.encontroId}" class="card-footer-item">Aceitar</a>
                            <a href="EncontroServlet?action=resposta-encontro&resp=false&encontroId=${enc.encontroId}" class="card-footer-item">Recusar</a>
                            <a onClick="expandirEncontro(${enc.solicitado.clienteId})" class="card-footer-item">Informações</a>

                            <div id="expand-encontro-${enc.solicitado.clienteId}" class="modal">
                                <div class="modal-background"></div>
                                <div class="modal-content encontro-details">
                                    <img src="ImageServlet?action=view&img=${enc.solicitado.clienteId}" alt="">
                                    <div class="encontro">
                                        <div>
                                            <p class="title">
                                                ${enc.solicitado.nome}
                                            </p>
                                            <p>
                                                ${enc.solicitado.descricao}
                                            </p>
                                            <p><strong>Idade:</strong> ${enc.solicitado.idade} anos</p>
                                            <p><strong>Altura:</strong> ${enc.solicitado.altura}cm</p>
                                            <hr>
                                            <p><strong>Dia:</strong> <fmt:formatDate type="date" value="${enc.dataEncontro}"/></p>
                                            <p><strong>Horário:</strong> <fmt:formatDate type="time" value="${enc.dataEncontro}"/></p>
                                            <p><strong>Local:</strong> ${enc.endereco.nomeLocal}</p>
                                        </div>

                                        <footer class="card-footer">
                                            <a href="EncontroServlet?action=resposta-encontro&resp=true&encontroId=${enc.encontroId}" class="card-footer-item">Aceitar</a>
                                            <a href="EncontroServlet?action=resposta-encontro&resp=false&encontroId=${enc.encontroId}" class="card-footer-item">Recusar</a>
                                        </footer>
                                    </div>

                                </div>
                                <button onClick="expandirEncontro(${enc.solicitado.clienteId})" class="modal-close is-large" aria-label="close"></button>
                            </div>
                          </footer>
                        </div>
                    </c:forEach>
                    <c:if test="${empty encontrosParaResponder}">
                        Nenhuma solicitação disponível
                    </c:if>

                </div>

            </div>

        </div>
        


    </body>
</html>
