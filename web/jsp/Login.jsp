<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- -->
        <form class="form-signin">
            <div class="text-center mb-4">
                <img class="mb-5" src="../logo4everlone.png" alt="" width="268" height="103">
                <h1 class="h3 mb-3 font-weight-normal">Forever Alone</h1>
            </div>
            <div class="form-label-group">
                <input type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus>
            </div>
            <div class="form-label-group">
                <input type="password" id="inputPassword" class="form-control" placeholder="Senha" required>
            </div>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Lembre-me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2018</p>
        </form>
    </body>
</html>

<style type="text/css">
    .form-signin{
        width: 100%;    
        max-width: 420px;
        padding: 30px;
        margin: 0 auto;
    }
    .form-label-group{
        margin-bottom: 1rem;
    }
    .form-label-group > label {
        position: absolute;
        top: 0;
        left: 0;
        display: block;
        width: 100%;
        margin-bottom: 0;
        line-height: 1.5;
        color: #495057;
        border: 1px solid transparent;
        border-radius: .25rem;
        transition: all .1s ease-in-out;
    }
    body{
        background-color: #b2bee2;
    }
</style>
