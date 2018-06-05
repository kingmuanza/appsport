<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App SPORT</title>
        <link href="css/metro.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css?id=6558" rel="stylesheet" type="text/css"/>
        <style>
            .fg-bleu{
                color: #1b4e9c;
            }
            .bg-bleu{
                background-color: #1b4e9c !important;
            }
            .fg-or{
                color: #f09433;
            }
            .bg-or{
                background-color: #f09433 !important;
            }
        </style>

        <script src="js/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script src="js/metro.js" type="text/javascript"></script>
        <script src="js/metro.js" type="text/javascript"></script>
        <script src="js/select2.min.js" type="text/javascript"></script>
    </head>

    <body class="metro keama_fixe">
        <div id="couche" style="background: linear-gradient(to right, #000046, #1cb5e0);  opacity: 0.9; position: absolute; height:100vh; width: 100vw;">
            
        </div>
        
        <div class="keama_milieu">
            <div class="fg-white lign-center">
                <div id="loading" class="row cells12">
                    <h1 class="keama_h1 fg-or">
                        <b>
                            <span class="fg-white">App'</span>SPORT
                        </b> 
                        <sup>
                            <span id="spinner" class="mif-spinner mif-ani-spin fg-white">
                                
                            </span></sup>
                    </h1>
                </div>
            </div>

            <form method="post" action="ConnexionServlet" class="grid">
                <div class="row cells12">
                    <div class="cell colspan5">
                        <div class="input-control text full-size info">
                            <span class="mif-user prepend-icon fg-bleu"></span>
                            <input id="login" name="login" type="text" placeholder="Login">
                            <button class="button helper-button clear"><span class="mif-cross"></span></button>
                        </div>
                    </div>
                    <div class="cell colspan5">
                        <div class="input-control password full-size info" data-role="input">
                            <span class="mif-lock prepend-icon fg-bleu"></span>
                            <input id="password" name="passe" type="password" placeholder="Mot de passe">
                            <button class="button helper-button clear"><span class="mif-cross"></span></button>
                        </div>
                    </div>
                </div>
                <div class="row cells12">
                    <input id="submit" onclick="showSpinner()" type="submit" class="button bg-or fg-white bd-orange" value="Connexion">
                </div>
            </form>
            
        </div>

    </body>

    <script>
        $(document).ready(function () {
            $('#spinner').hide();
            <c:if test="${!empty sessionScope.error}">
            $.Notify({
                content: '\n Login ou Mot de passe incorrect. Veuillez contacter l\'administrateur pour obtenir des paramètres de connexion',
                timeout: 10000,
                type: 'alert'
            });
            </c:if>
        });

        function showSpinner() {
            $('#spinner').show();
            $('#submit').hide();
            $('#couche').animate({
                opacity: 0.9
            }, 1000, function(){
            });
        }
    </script>
</html>
