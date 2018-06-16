<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar festa</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>
        <script src="js/forms.js"></script>
        
        <script>
            var clientes = [];
            var carregando = false;
            
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
                            var appendHtml = '<option value=' + obj.idCidade + '>' + obj.nome + '</option>';
                            $("#cidade").append(appendHtml);
                        });
                    },
                    error : function(request, textStatus, errorThrown) {
                        alert(request.status + ', Error: ' + request.statusText);
                    }
                });
            }       
            
            function getLocais(){
                var ci = $("#cidade").val();
                var url = "AJAXServlet?action=viewLocais";
                $.ajax({
                    url : url, // URL da sua Servlet
                    data : {
                        cidadeId: ci
                    }, // Parâmetro passado para a Servlet
                    dataType : 'json',
                    success : function(data) {
                        $("#local").empty();
                        $.each(data, function(i, obj) {
                            var appendHtml = '<option value=' + obj.enderecoId + '>' + obj.nomeLocal + '</option>';
                            $("#local").append(appendHtml);
                        });
                    },
                    error : function(request, textStatus, errorThrown) {    
                        alert(request.status + ', Error: ' + request.statusText);
                    }
                });
            }      
            
            $(function() {
                $("#search").on("keyup", function() {
                    var value = $(this).val().toLowerCase();
                    $("#clienteTable tbody tr").filter(function() {
                      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
                $( "#estado" ).change(function() {
                    getCidades();
                });
                
                $( "#cidade" ).change(function() {
                    getLocais();
                });
            });
            
            function selecionarCliente(id, checkbox) {
                if(checkbox.checked){
                    clientes.push({ id: id });
                } else {
                    clientes = clientes.filter(function(c) {
                        return c.id !== id;
                    });
                }
            }
            
            function expandirClientes() {
                $("#expand").toggleClass("is-active");
                return false;
            }
            
            function getFormData($form){
                var unindexed_array = $form.serializeArray();
                var indexed_array = {};

                $.map(unindexed_array, function(n, i){
                    indexed_array[n['name']] = n['value'];
                });

                return indexed_array;
            }
            
            function submitForm() {
                if (carregando) return false;
                
                var jsonObj = getFormData($("#myForm"));
                var cli = "";
                for (var i = 0; i < clientes.length; i++) {
                    cli += clientes[i].id;
                    if (i < clientes.length-1) {
                        cli += ';';
                    }
                }
                jsonObj.clientes = cli;
                carregando = true;
                $("#loading").toggleClass("is-active");
                $("#botoes").hide();
                $.ajax({
                    url: 'FestaServlet?action=new',
                    type: 'post',
                    dataType: 'json',
                    data: jsonObj,
                    success: function(data) {
                        carregando = false;
                        $("#loading").toggleClass("is-active");
                        $("#botoes").show();
                        if (data.ok) {
                            $("#success").toggleClass("is-active");
                        }
                    },
                    error: function() { 
                        carregando = false;
                        $("#loading").toggleClass("is-active");
                        $("#botoes").show();
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
                    <h1 class="title">
                        Cadastre uma nova festa
                    </h1>
                </div>
            </div>
        </section>
        <div class="container margem">
            <form id="myForm" method="post" action="#">                        
                <div class="columns">
                    <div class="field column">
                        <label class="label">Tema da festa:</label>
                        <div class="control">
                            <input name="tema" class="input" type="text"
                                   placeholder="Tema da festa (titulo)">
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Numero de vagas:</label>
                        <div class="control">
                            <input name="vagas" class="input" type="text"
                                   placeholder="Número de vagas">
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
                                      <option value="${es.idEstado}">(${es.sigla}) ${es.nome}</option>
                                  </c:forEach>
                              </select>
                            </div>
                        </div>
                    </div>

                    <div class="column">
                        <div class="field">
                            <label class="label" for="cidadeId">Cidade</label>
                            <div class="select">
                              <select id="cidade" name="idCidade">
                              </select>
                            </div>
                        </div>
                    </div>

                    <div class="column">
                        <div class="field">
                            <label class="label" for="localId">Local</label>
                            <div class="select">
                              <select id="local" name="localId">
                              </select>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="columns">
                    <div class="field column">
                        <label class="label">Data da festa</label>
                        <div class="control has-icons-left">
                            <input name="dataFesta" class="input" type="text" id="datepicker"
                                   placeholder="Selecione a data da festa">
                            <span class="icon is-left">
                                <i class="fas fa-calendar-alt"></i>
                            </span> 
                        </div>
                    </div>

                    <div class="field column">
                        <label class="label">Horário da festa:</label>
                        <div class="control has-icons-left">
                            <input name="horario" class="input" type="text" id="horario"
                                   placeholder="00:00">
                            <span class="icon is-left">
                                <i class="fas fa-clock"></i>
                            </span> 
                        </div>
                    </div>


                </div>
                <div class="field">
                    <label class="label">Descrição:</label>
                    <div class="control">
                        <textarea rows="4" name="descricao" class="textarea"
                                  placeholder="Descrição sobre a festa"></textarea>
                    </div>
                </div>
                
                <div id="botoes">
                    <button type="button" onClick="submitForm()" class="button is-info">Cadastrar festa</button>
                    <button type="button" onClick="expandirClientes()" class="button is-info">Convidar clientes específicos</button>
                </div>
 
                <div id="expand" class="modal">
                    <div class="modal-background"></div>
                    <div class="modal-content cliente-modal">
                        <div class="field column">
                            <div class="control">
                                <label class="label">Pesquise por qualquer atributo:</label>
                                <input id="search" name="search" class="input" type="text"
                                       placeholder="Digite o atributo">
                            </div>
                        </div>
                        
                        <table id="clienteTable" class="table">
                            <thead>
                              <tr>
                                  <th>Nome</th>
                                  <th>Sexo</th>
                                  <th>Cpf</th>
                                  <th>Idade</th>
                                  <th>Altura</th>
                                  <th>Escolaridade</th>
                                  <th>Cidade</th>
                                  <th>Cor Pele</th>
                                  <th>Cor Cabelo</th>
                                  <th>Selecionar</th>
                              </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${clientesList}" var="c">
                                    <tr>
                                        <td>${c.nome}</td>
                                        <td>${c.sexo}</td>
                                        <td>${c.cpf}</td>
                                        <td>${c.idade} anos</td>
                                        <td>${c.altura} cm</td>
                                        <td>${c.escolaridade.descricao}</td>
                                        <td>${c.endereco.cidade.nome}</td>
                                        <td>${c.corPele.cor}</td>
                                        <td>${c.corCabelo.cor}</td>
                                        <td>   
                                            <label class="checkbox">
                                                <input onchange="selecionarCliente(${c.clienteId}, this)" name="select" type="checkbox">
                                                Select
                                            </label>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <button type="button" onClick="expandirClientes()" class="modal-close is-large" aria-label="close"></button>
                </div>
            </form>
        </div>
        <div id="loading" class="modal">
            <div class="modal-background"></div>
            <div style="text-align: center" class="modal-content">
                <img src="images/loading.svg">
            </div>
        </div>
        
        <div id="success" class="modal">
            <div class="modal-background"></div>
            <div class="modal-content cliente-modal">
                <center style="padding: 3em">
                    <h1 class="title">Festa criada e convites enviados com sucesso!</h1>
                    <a href="HomeServlet">Clique para voltar à Home</a>
                </center>
            </div>
        </div>
    </body>
</html>
