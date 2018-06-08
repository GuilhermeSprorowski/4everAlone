<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <c:import url="../components/header.jsp" ></c:import>
        
        <section class="hero is-info">
            <div class="hero-body">
              <div class="container">
                <h1 class="title">
                  Se apaixonou e deseja se casar com a pessoa?
                </h1>
                <h2 class="subtitle">
                  Então planeje seu casamento conosco!
                </h2>
                <button class="button">Solicitar orçamento</button>
              </div>
            </div>
        </section>
        <div class="container margem">
            <h1 class="title">Orçamento solicitado</h1>
            
            <c:if test="empty orcamentos">
                <p>Nenhum orçamento disponível</p>
            </c:if>
                
            <!-- FOREACH AQUI -->
            <div class="card">
                <table width="100%" class="table">
                    <thead>
                      <tr>
                        <th>Descrição</th>
                        <th>Valor</th>
                      </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Valor total:</th>
                        <th>1500 R$</th>
                    </tr>
                  </tfoot>

                    <tbody>
                        <tr>
                          <td>Bolo de casamento</td>
                          <td>1500,00</td>
                        </tr>
                    </tbody>
                    
                </table>
                <footer class="card-footer">
                    <a href="#" class="card-footer-item">Aceitar orçamento</a>
                    <a href="#" class="card-footer-item">Recusar</a>
                </footer>
            </div>
        </div>
    </body>
</html>
