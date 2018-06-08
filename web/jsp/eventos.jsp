<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <!-- FOR EACH AQUI -->
            <div class="list-item">
                <div class="flex-row padding-bottom">
                    <p>
                        Dia: 25/07/2018
                    </p>
                    <div class="descricao">
                        <h1 class="title">Balada toperson</h1>
                        <p>
                            A *paragraph* of {color:red}text{color} with an [unassigned link].
                            A _second_ &row& of ~text~ with a [web link]
                            (https://docs.balsamiq.com/desktop/text/#basic-formatting)
                        </p>
                    </div>
                    <p>
                        Horário: 20:00
                    </p>
                </div>

                <footer class="card-footer">
                    <a href="#" class="card-footer-item">Confirmar presença</a>
                    <a href="#" class="card-footer-item">Não comparecerei</a>
                </footer>
            </div>
        </div>
    </body>
</html>
