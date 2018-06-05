<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App's SPORT</title>
        <!--        <link href="css/keama.css" rel="stylesheet" type="text/css"/>
                <link href="css/metro.css" rel="stylesheet" type="text/css"/>
                <link href="css/select2.css" rel="stylesheet" type="text/css"/>        
                <script src="js/jquery-3.1.0.js" type="text/javascript"></script>
                <script src="js/metro.js" type="text/javascript"></script>
                <script src="js/select2.min.js" type="text/javascript"></script>-->
        <script>
            $("#cadre").click(function () {
                $("#imgInp").click();
                console.log("Je clique nooh")
            });

            function readURL(input) {

                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#blah').attr('src', e.target.result);
                        console.log(e.target.result);
                        $("input[name='imgblob']").val(e.target.result)
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#imgInp").change(function () {
                readURL(this);
            });

            $("#mySelect2").focusout(function () {
                var id = $("select[name=disciplines]").val();
                console.log("discipline: " + id);
                loadCategorie(id);
                $('select[name=categorie] option').removeAttr("selected");
            });

            function  loadCategorie(id) {
                var time = new Date().getTime();
                $("select[name=categorie]").load("CategoriresAjaxServlet?id=" + id + "&time=" + time, function () {

                    console.log("Load was performed.");

                });
            }




        </script>


    </head>
    <body >
        <div class="grid">

        </div>

        <div  style="font-size: 1.8em; padding-bottom: 25px;">
            Changer le mot de passe
        </div>

        <form id="form1" runat="server" class="grid" method="post" action="ChangePasseServlet" style="padding-top: 0px ;">
            <div class="row">
                <div class="cell">
                    <label>Mot de passe</label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="passe" type="password" value="${u.passe}" required="true">
                    </div>
                </div>
            </div>
            <div class="row cells2">
                <div class="cell ">
                    <label>Nouveau Mot de passe</label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="nouveau" type="password" required="true">
                    </div>
                </div>
                <div class="cell ">
                    <label>Confirmation nouveau ot de passe</label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="confirme" type="password" required="true">
                    </div>
                </div>
            </div>
                    <div class="row cells6">
                <div class="cell colspan6">
                    <br>
                    <input type="submit" value="Enregistrer" class="button fg-white bg-blue bd-blue" >
                    
                </div>
            </div>
        </form>
    </body>
</html>
