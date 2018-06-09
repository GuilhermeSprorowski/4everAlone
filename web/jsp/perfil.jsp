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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="js/forms.js"></script>
        
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
                  $( "#altura-amount" ).val( "" + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
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
                            <c:if test = "${form == 'alterar'}">${cliente.endereco.cidade.idCidade}</c:if>
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
        <c:import url="../components/header.jsp" ></c:import>
        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <form method="POST" class="flex-row" action="ImageServlet?action=salva&img=${cliente.clienteId}" enctype='multipart/form-data'>
                        <div class="titulo-perfil">
                            <h1 class="title">
                                ${cliente.nome}
                            </h1>
                            <h2 class="subtitle">
                                ${cliente.cpf}
                            </h2>
                        </div>
                        
                        <div>
                            <img style="width: 256px    " src="ImageServlet?action=view&img=${cliente.clienteId}"/><br>
                            <input type="file" class="button is-link" name="file" id="foto"/>
                            <input type="submit" class="button is-link" value="Enviar Foto" />
                        </div>
                    </form>
                    
                </div>
            </div>
        </section>
        <div class="container margem">            
            <form method="POST" action="ClienteServlet?action=salva" class="column card">               
                <div class="columns">
                    <div class="column">
                        <div class="columns">
                            <div class="field column">
                                <label class="label">Data de nascimento:</label>
                                <div class="control has-icons-left">
                                    <input name="dataNasc" class="input" type="text" id="datepicker"
                                           placeholder="Data nascimento" value="${cliente.dataNascS}">
                                    <span class="icon is-left">
                                        <i class="fas fa-calendar-alt"></i>
                                    </span> 
                                </div>
                            </div>
                            <div class="field column">
                                <label class="label">Altura (em cm):</label>
                                <div class="control">
                                    <input name="altura" class="input" type="text"
                                           placeholder="Altura (em cm)" value="${cliente.altura}">
                                </div>
                            </div>
                        </div>
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
                                            <c:if test="${cliente.corPele.idCorPele == cp.idCorPele}">selected</c:if>
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
                                            <c:if test="${cliente.corCabelo.idCorCabelo == cc.idCorCabelo}">selected</c:if>
                                          >${cc.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <div class="field">
                                    <label class="label" for="idEstado">Estado</label>
                                    <div class="select">
                                      <select id="estado" name="idEstado">
                                          <c:forEach items="${estados}" var="es">
                                              <option value="${es.idEstado}"
                                                <c:if test="${form == 'alterar' && cliente.endereco.estado.idEstado == es.idEstado}">selected</c:if>
                                              >(${es.sigla}) ${es.nome}</option>
                                          </c:forEach>
                                      </select>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="idEndereco" value="${cliente.endereco.enderecoId}"/>
                            <div class="column">
                                <div class="field">
                                    <label class="label" for="cidadeId">Cidade</label>
                                    <div class="select">
                                      <select id="cidade" name="idCidade">
                                      </select>
                                    </div>
                                </div>
                            </div>
                            
                        </div>

                        <div class="field">
                            <label class="label" for="rua">Endereço:</label>
                            <input class="input" type="text" name="rua" value="${cliente.endereco.rua}"/>
                        </div>
                        
                        <div class="field">
                            <label class="label" for="descricao">Descrição:</label>
                            <textarea rows="3" class="textarea" type="text" name="descricao">${cliente.descricao}</textarea>
                        </div>
                    </div>
                    <div class="column card">
                        <p class="title is-4">Preferências</p>
                        <label class="label" for="psexo">Preferência sexual:</label>
                        <div class="select">
                          <select name="psexo">
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
                                <label class="label" for="pcorPele">Cor da Pele</label>
                                <div class="select">
                                  <select name="pcorPele">
                                      <c:forEach items="${corPele}" var="cp">
                                          <option value="${cp.idCorPele}"
                                            <c:if test="${cliente.preferencias.corPele.idCorPele == cp.idCorPele}">selected</c:if>
                                          >${cp.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                            <div class="column field">
                                <label class="label" for="pcorCabelo">Cor do Cabelo</label>
                                <div class="select">
                                  <select name="pcorCabelo">
                                      <c:forEach items="${corCabelo}" var="cc">
                                          <option value="${cc.idCorCabelo}"  
                                            <c:if test="${cliente.preferencias.corCabelo.idCorCabelo == cc.idCorCabelo}">selected</c:if>
                                          >${cc.cor}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                        <div class="column field">
                            <p>
                                <label for="pidade">Idade:</label>
                                <input type="text" id="idade-amount" name="pidade" readonly style="border:0; color:#209cee; font-weight:bold;">
                            </p>

                            <div id="idade-range"></div>
                        </div>
                            
                        <div class="column field">
                            <p>
                                <label for="paltura">Altura (cm):</label>
                                <input type="text" id="altura-amount" name="paltura" readonly style="border:0; color:#209cee; font-weight:bold;">
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
