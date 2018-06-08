<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                $("#expand-encontro-id").toggleClass("is-active");
            } 
        </script>
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>

        <div class="container margem">
            <div class="columns is-2">
                <div class="column">
                    <h1 class="notification is-info">Solicitações enviadas</h1>
                    <!-- Será feito o foreach Aqui -->
                    <div class="media">
                        <figure class="media-left">
                          <p class="image is-64x64">
                            <img src="https://bulma.io/images/placeholders/128x128.png">
                          </p>
                        </figure>
                        <div class="media-content">
                          <div class="content">
                            <p>
                              <strong>John Smith</strong>
                              <br>
                              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.
                            </p>
                          </div>
                          <nav class="level is-mobile">
                            <div class="level-left">
                                <strong>Aguardando cofirmação...</strong>
                            </div>
                          </nav>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <h1 class="notification is-success" >Solicitações recebidas</h1>
                    <!-- Será feito o foreach Aqui -->
                    <div class="encontro flex-column">
                        <p class="title">
                          Laura
                        </p>
                      <div class="descricao">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.
                      </div>    
                      <footer class="card-footer">
                        <a href="#" class="card-footer-item">Aceitar</a>
                        <a href="#" class="card-footer-item">Recusar</a>
                        <a onClick="expandirEncontro()" class="card-footer-item">Informações</a>
                        
                        <div id="expand-encontro-id" class="modal">
                            <div class="modal-background"></div>
                            <div class="modal-content encontro-details">
                                <img src="https://image.afcdn.com/story/20150627/laura-escanes-701071_w767h767c1cx101cy179.jpg" alt="">
                                <div class="encontro">
                                    <div>
                                        <p class="title">
                                            Laura
                                        </p>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.
                                        </p>
                                        <p><strong>Altura:</strong> 170cm</p>
                                        <hr>
                                        <p><strong>Horário:</strong> 20h</p>
                                        <p><strong>Local:</strong> Rua etc etc</p>
                                    </div>

                                    <footer class="card-footer">
                                        <a href="#" class="card-footer-item">Aceitar</a>
                                        <a href="#" class="card-footer-item">Recusar</a>
                                    </footer>
                                </div>
                                
                            </div>
                            <button onClick="expandirEncontro()" class="modal-close is-large" aria-label="close"></button>
                        </div>
                      </footer>
                    </div>
                   
                </div>

            </div>

        </div>
        


    </body>
</html>
