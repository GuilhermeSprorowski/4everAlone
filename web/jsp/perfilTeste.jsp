<%-- 
    Document   : perfilTeste
    Created on : 19/05/2018, 10:14:22
    Author     : T-Gamer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="${pageContext.request.contextPath}/HomeServlet" enctype='multipart/form-data'>
           <input type="file" name="file" id="foto" multiple/>
            <input type="submit" value="Cadastrar Usuário" />
        </form>
    </body>
</html>
