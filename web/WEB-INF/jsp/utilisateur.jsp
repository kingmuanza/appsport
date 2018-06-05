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
            <div class="row cells6">
                <div class="cell " id="cadre" style="padding: 1px; border: 1px solid #f09433;border-radius: 0px ; min-height: 40px;">
                    <img  id="blah" src="${empty u.photo ? 'img/user.png': blobToString.generate(u.photo)}" class="bd-white muan" style="border: 1px solid">
                </div>
                <div class="cell colspan5">
                    <div class="fg-bleu" style="font-size: 1.8em; padding-bottom: 5px;">
                        <b>
                            ${empty u ? "Nouvel":u.noms} ${empty u ? "utilisateur":u.prenoms}
                        </b>
                    </div>
                    <div class="fg-or" style="font-size: 1.2em; padding-bottom: 10px;">
                        <b>
                            ${empty u.organisation ? "Aucune Organisation":u.organisation.libelle}
                        </b>
                    </div>
                    <small style="font-size: 0.7em">Les champs avec <span class="fg-red">*</span> sont obligatoires</small>
                </div>
            </div>
        </div>


        <form id="form1" runat="server" class="grid" method="post" action="UtilisateurServlet" style="padding-top: 0px ;">
            <input type='file' name="imageenvoyee" id="imgInp" style="display: none"/>
            <div class="row cells4">
                <div class="cell colspan2">
                    <label>Noms<span class="fg-red">*</span></label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="noms" type="text" value="${u.noms}" required="true">
                        <input id="imgblob" type="hidden" name="imgblob" value="${blobToString.generate(u.photo)}">
                        <input name="id" type="hidden" value="${u.idutilisateur}" >
                    </div>
                </div>
                <div class="cell colspan2">
                    <label>Prénoms</label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="prenoms" type="text" value="${u.prenoms}" required="true">
                    </div>
                </div>
            </div>
            <div class="row cells4">
                <div class="cell colspan2">
                    <label>Login<span class="fg-red">*</span></label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="login" type="text" value="${u.login}" required="true">
                    </div>
                </div>
                <div class="cell colspan2">
                    <label>Mot de passe</label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="passe" type="password" value="${u.passe}" required="true">
                    </div>
                </div>
            </div>
            <div class="row cells2">
                <div class="cell">
                    <label>Profil <span class="fg-red">*</span></label>
                    <div class="input-control select full-size" data-role="select" data-placeholder="Entrez la qualité">
                        <select name="profil" class="" required="true">
                            <option></option>
                            <c:forEach items="${utilisateurProfils}" var="q">
                                <option ${u.utilisateurProfil.idutilisateurProfil==q.idutilisateurProfil ? "selected":""} value="${q.idutilisateurProfil}">
                                    ${q.libelle}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="cell">
                    <label>Organisation<span class="fg-red">*</span></label>
                    <div class="input-control select full-size" data-role="select" data-placeholder="Entrez le nom de l'organisation">
                        <select name="organisation" class="js-select full-size" required="true">
                            <option></option>
                            <c:forEach items="${organisations}" var="r">
                                <option ${u.organisation.idorganisation==r.idorganisation ? "selected":""} value="${r.idorganisation}">
                                    ${r.libelle}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="row cells6">
                <div class="cell colspan6">
                    <br>
                    <input type="submit" value="Enregistrer" class="button fg-white bg-bleu" >
                    <c:if test="${!empty u}">
                        <button style="" type="submit" name="action" value="supprimer" class="button bg-or fg-white bd-white"> 
                            Supprimer
                        </button>
                    </c:if>
                </div>
            </div>
        </form>
    </body>
</html>
