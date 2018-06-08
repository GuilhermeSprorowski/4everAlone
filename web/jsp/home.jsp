<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>

        <div class="container margem">
            <div class="columns is-2">
                <div class="column card">
                    <h1 class="notification is-info">Eventos marcados</h1>
                    <!-- Será feito o foreach Aqui -->
                    <div class="card">
                      <header class="card-header">
                        <p class="card-header-title title">
                          Titulo
                        </p>
                      </header>
                      <div class="card-content card-content-limit">
                        <div class="content">
                          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                        </div>
                      </div>
                      <footer class="card-footer">
                        <a href="#" class="card-footer-item">Aceitar</a>
                        <a href="#" class="card-footer-item">Recusar</a>
                      </footer>
                    </div>
                </div>
                <div class="column card">
                    <h1 class="notification is-success" >Encontros Pendentes </h1>
                    <!-- Será feito o foreach Aqui -->
                    <div class="card">
                      <header class="card-header">
                        <p class="card-header-title title">
                          Titulo
                        </p>
                      </header>
                      <div class="card-content card-content-limit">
                        <div class="content">
                          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                        </div>
                      </div>
                      <footer class="card-footer">
                        <a href="#" class="card-footer-item">Aceitar</a>
                        <a href="#" class="card-footer-item">Recusar</a>
                      </footer>
                    </div>
                </div>

            </div>

        </div>

    </body>
</html>
