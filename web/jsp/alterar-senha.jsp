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
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>
        <script src="js/forms.js"></script>
        <title>Alterar Senha</title>
    </head>
    <body>
        <div class="container margem"  style="width: 360px; margin: 0 auto;">      

            <div class="column">
                <div class="field column">
                    <label class="label">Senha Atual</label>
                    <div class="control">
                        <input name="senhaAtual" id="senhaAtual" class="input" type="password" placeholder="Senha Atual..." required="required">
                    </div>
                </div>
                <div class="field column">
                    <label class="label">Nova Senha:</label>
                    <div class="control">
                        <input name="senhaNova" id="novasenha" class="input" type="password" placeholder="Nova Senha..." required="required">
                    </div>
                </div>
                <div class="field column">
                    <label class="label">Confirme a Senha:</label>
                    <div class="control">
                        <input class="input" id="confirma" type="password" placeholder="Cofirme a senha..." required="required">
                    </div>
                </div>
            </div>
            <div class="field is-grouped is-grouped-right">
                <button class="button is-info" id="salvar">Salvar</button>
            </div>
        </div>
    </body>
    <script>
        $("#salvar").click(function () {
            if ($("#confirma").val() != $("#novasenha").val()) {
                alert("a confirmação esta errada! Por favor digite novamente");
            } else {
                $.ajax({
                    type: 'POST',
                    url: "ClienteServlet?action=salvaSenha",
                    data: {
                        "senhaAtual": $("#senhaAtual").val(),
                        "senhaNova": $("#novasenha").val()
                    },
                    error: function () {
                        alert("senha incorreta!");
                    },
                    success: function () {
                        alert("Senha Alterado com Sucesso");
                        window.location.assign("ClienteServlet?action=view");
                    }

                });
            }
        });

    </script>
</html>
