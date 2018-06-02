<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <c:import url="../components/header.html" ></c:import>

        <div class="container margem">
            <div class="columns is-2">
                <div class="column card">
                    <h1 class="notification is-info">Convites para encontros</h1>
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
                    <h1 class="notification is-success" >Encontros Pendentes</h1>
                    <!-- Será feito o foreach Aqui -->
                    <div class="card">
                      <header class="card-header">
                          <img/>
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
