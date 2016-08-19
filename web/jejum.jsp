<%-- 
    Document   : jeum
    Created on : 18/08/2016, 09:07:43
    Author     : CARLOS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pacientes em Jejum</title>
        <meta HTTP-EQUIV="refresh" CONTENT="30"> 
        <link href="<c:url value="/css/bootstrap.min.css" /> rel="stylesheet">
        <link href="<c:url value="/css/style.css" /> rel="stylesheet">
        <link rel="shortcut icon" href="img/ham.png">
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <!--<span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        -->
                    </button>
                    <a class="navbar-brand" href="#">Protocolo de Jejum</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="servicos/excel1.php">Baixar em Excel</a></li>
                        <li><a href="servicos/pdf.php">Baixar em PDF</a></li>
                        <!--<li><a href="servicos/mail.php">E-mail</a></li>-->                  
                    </ul>
                </div>
            </div>
        </nav>
        <hr />
        <div id="main" class="container-fluid">
            <div id="list" class="row">
                <div class="table-responsive col-md-12">
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th>PACIENTE</th>
                                <th>UNIDADE</th>
                                <th>LEITO</th>
                                <th>JEJUM</th>
                                <th>INICIO DO JEJUM</th>
                                <th>OBSERVA&Ccedil;&Atilde;O</th>
                                <th>SITUA&Ccedil;&Atilde;O</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="jejum" items="${jejum}">
                                <tr>
                                    <td>${jejum.paciente}</td>
                                    <td>${jejum.unidade}</td>
                                    <td>${jejum.leito}</td>
                                    <td>${jejum.jejum}</td>
                                    <td>${jejum.inicio_Jejum}</td>
                                    <td>${jejum.obs}</td>
                                    
                                    <c:choose>
                                        <c:when test="${jejum.situacao == 'G'}">
                                            <c:set var="cor" value="verde" />                                            
                                            <c:set var="tempo" value="Menor que 4 horas" />                                            
                                        </c:when>    
                                        <c:when test="${jejum.situacao == 'Y'}">
                                             <c:set var="cor" value="amarela" />     
                                             <c:set var="tempo" value="Entre 4 e 5 horas" />
                                        </c:when>
                                        <c:when test="${jejum.situacao == 'R'}">
                                             <c:set var="cor" value="vermelha" />     
                                             <c:set var="tempo" value="Maior que 5 horas" />
                                        </c:when>
                                    </c:choose>
                                    <td><center><img src='<c:url value="img/${cor}.png" />' width=20 height=20 title='${tempo}'></center></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>                   
        
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
