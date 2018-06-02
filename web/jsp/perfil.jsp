<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        
        <script>
        $(document).ready(function() {
            $( "#estado" ).change(function() {
                getCidades();
            });
            
            var valuesIdade = [
                <c:if test="${form == 'alterar'}">
                    ${cliente.preferencias.idade[0]}, ${cliente.preferencias.idade[1]}
                </c:if>
                <c:if test="${form != 'alterar'}">
                    18, 25
                </c:if>
            ];
            var valuesAltura = [
                <c:if test="${form == 'alterar'}">
                    ${cliente.preferencias.altura[0]}, ${cliente.preferencias.altura[1]}
                </c:if>
                <c:if test="${form != 'alterar'}">
                    150, 190
                </c:if>
            ];
            $( "#idade-range" ).slider({
                range: true,
                min: 16,
                max: 120,
                values: valuesIdade,
                slide: function( event, ui ) {
                  $( "#idade-amount" ).val( "" + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
                }
            });
              
            $( "#idade-amount" ).val( "" + $( "#idade-range" ).slider( "values", 0 ) +
              " - " + $( "#idade-range" ).slider( "values", 1 ) );
      
            $( "#altura-range" ).slider({
                range: true,
                min: 100,
                max: 230,
                values: valuesAltura,
                slide: function( event, ui ) {
                  $( "#altura-amount" ).val( "" + ui.values[ 0 ] + " - " + ui.values[ 1 ] + " cm" );
                }
            });
              
            $( "#altura-amount" ).val( "" + $( "#altura-range" ).slider( "values", 0 ) +
              " - " + $( "#altura-range" ).slider( "values", 1 ) );
            
            <c:if test = "${form == 'alterar'}">getCidades()</c:if>
        });
        
        function getCidades(){
            var estadoId = $("#estado").val();
            var url = "AJAXServlet?action=viewCidade";
            $.ajax({
                url : url, // URL da sua Servlet
                data : {
                    estadoId: estadoId
                }, // Parâmetro passado para a Servlet
                dataType : 'json',
                success : function(data) {
                    $("#cidade").empty();
                    $.each(data, function(i, obj) {
                        var cidadeId = 
                            <c:if test = "${form == 'alterar'}">${cliente.endereco.cidadeId}</c:if>
                            <c:if test = "${form != 'alterar'}">null</c:if>;
                        var appendHtml = '<option ' +
                                    (cidadeId === obj.idCidade ? 'selected' : '')
                                    + ' value=' + obj.idCidade + '>' + obj.nome + '</option>';
                        $("#cidade").append(appendHtml);
                    });
                },
                error : function(request, textStatus, errorThrown) {
                    alert(request.status + ', Error: ' + request.statusText);
                }
            });
        }        
        </script>
    </head>
    <body>
        <c:import url="../components/header.html" ></c:import>
        <div class="container margem">
            <form method="POST" action="ClienteServlet?action=salva" class="column card">
                <h1 class="perfil notification is-info">
                    <img width="128px" src="https://3s.orisom.com/statics/bootstrap-3.2.0/img/img-perfil.jpg"/>
                    ${cliente.nome} - ${cliente.cpf}
                </h1>
                <div class="columns">
                    <div class="column">
                        <div class="columns">
                            <div class="column field">
                                <label class="label" for="escolaridade">Escolaridade</label>
                                <div class="select">
                                  <select name="escolaridade">
                                      <c:forEach items="${escolaridade}" var="es">
                                          <option value="${es.idEscolaridade}"
                                            <c:if test="${form == 'alterar' && cliente.escolaridade == es.descricao}">selected</c:if>
                                          >${es.descricao}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                            <div class="column field">
                                <label class="label" for="corPele">Cor da Pele</label>
                                <div class="select">
                                  <select name="corPele">
                                      <c:forEach items="${corPele}" var="cp">
                                          <option value="${cp.idCorPele}"
                                            <c:if test="${cliente.corPele == cp.cor}">selected</c:if>
                                          >${cp.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                            <div class="column field">
                                <label class="label" for="corCabelo">Cor do Cabelo</label>
                                <div class="select">
                                  <select name="corCabelo">
                                      <c:forEach items="${corCabelo}" var="cc">
                                          <option value="${cc.idCorCabelo}"  
                                            <c:if test="${cliente.corCabelo == cc.cor}">selected</c:if>
                                          >${cc.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <div class="field">
                                    <label class="label" for="endereco.estadoId">Estado</label>
                                    <div class="select">
                                      <select id="estado" name="endereco.estadoId">
                                          <c:forEach items="${estados}" var="es">
                                              <option value="${es.idEstado}"
                                                <c:if test="${form == 'alterar' && cliente.endereco.estadoId == es.idEstado}">selected</c:if>
                                              >(${es.sigla}) ${es.nome}</option>
                                          </c:forEach>
                                      </select>
                                    </div>
                                </div>
                            </div>
                            <div class="column">
                                <div class="field">
                                    <label class="label" for="endereco.cidadeId">Cidade</label>
                                    <div class="select">
                                      <select id="cidade" name="endereco.cidadeId">
                                      </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <label class="label" for="endereco.rua">Endereço:</label>
                            <input class="input" type="text" name="endereco.rua" value="${cliente.endereco.rua}"/>
                        </div>
                        <div class="field">
                            <label class="label" for="descricao">Descrição:</label>
                            <textarea rows="3" class="textarea" type="text" name="descricao" value="${cliente.descricao}"></textarea>
                        </div>
                    </div>
                    <div class="column card">
                        <p class="title is-4">Preferências</p>
                        <label class="label" for="preferencias.sexo">Preferência sexual:</label>
                        <div class="select">
                          <select name="preferencias.sexo">
                            <option value="m" 
                                <c:if test="${cliente.preferencias.sexo == 'm'}">selected</c:if>
                            >Masculino</option>
                            <option value="f"
                                <c:if test="${cliente.preferencias.sexo == 'f'}">selected</c:if>
                            >Feminino</option>
                          </select>
                        </div>
                        <div class="columns">
                            <div class="column field">
                                <label class="label" for="preferencias.corPele">Cor da Pele</label>
                                <div class="select">
                                  <select name="preferencias.corPele">
                                      <c:forEach items="${corPele}" var="cp">
                                          <option value="${cp.idCorPele}"
                                            <c:if test="${cliente.preferencias.corPele == cp.cor}">selected</c:if>
                                          >${cp.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                            <div class="column field">
                                <label class="label" for="preferencias.corCabelo">Cor do Cabelo</label>
                                <div class="select">
                                  <select name="preferencias.corCabelo">
                                      <c:forEach items="${corCabelo}" var="cc">
                                          <option value="${cc.idCorCabelo}"  
                                            <c:if test="${cliente.preferencias.corCabelo == cc.cor}">selected</c:if>
                                          >${cc.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                        <div class="column field">
                            <p>
                                <label for="preferencias.idade">Idade:</label>
                                <input type="text" id="idade-amount" name="preferencias.idade" readonly style="border:0; color:#209cee; font-weight:bold;">
                            </p>

                            <div id="idade-range"></div>
                        </div>
                            
                        <div class="column field">
                            <p>
                                <label for="preferencias.altura">Altura (cm):</label>
                                <input type="text" id="altura-amount" name="preferencias.altura" readonly style="border:0; color:#209cee; font-weight:bold;">
                            </p>

                            <div id="altura-range"></div>
                        </div>
                    </div>
                </div>
                <div class="field is-grouped">
                    <div class="control">
                      <button type="submit" class="button is-link">Salvar</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
