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
                $('#categorielabel').fadeTo("slow", 0.5);
                $('#spinnerlabel').css("display", "block");
                var time = new Date().getTime();
                $("select[name=categorie]").load("CategoriresAjaxServlet?id=" + id + "&time=" + time, function () {
                    $('#categorielabel').fadeTo("slow", 1);
                    $('#spinnerlabel').css("display", "none");
                    console.log("Load was performed.");

                });
            }


            function loadPhoto(id) {
                var time = new Date().getTime();
                $.get("IndividuPhotoServlet?id=" + id + "&time=" + time, function (data) {
                    console.log(data)
                    $("#blah").attr("src", data);
                    $("input[name='imgblob']").val(data)
                });
            }

            $(document).ready(function () {
                loadPhoto(${individu.idindividu});
                $("#chargement").hide("slow");
            })




        </script>
        <style>
            #blah:hover{
                opacity: 0.8;
            }
        </style>


    </head>
    <body >
        <div class="grid">
            <div class="row" style="padding-bottom: 20px;">
                <div class="fg-bleu" style="font-size: 3.0em; padding-bottom: 5px;">
                    <b>
                        ${empty individu ? "Nouvel":individu.nom} ${empty individu ? "individu":individu.prenom}
                    </b>
                </div>
                <div class="fg-or" style="font-size: 1.8em; padding-bottom: 5px;">
                    <b>
                        ${empty individu.organisation ? "Aucune Organisation":individu.organisation.libelle}
                    </b>
                </div>
            </div>
            <div class="row cells3">
                <div class="cell" id="cadre" style="cursor : pointer; padding-top: 40px; border: 0px solid #f09433;border-radius: 0px ; min-height: 100px;">
                    <img  id="blah" src="img/user.png" class="bd-white muan" style="width: 100%">
                </div>
                <div class="cell padding20 colspan2">
                    <form id="form1" runat="server" class="grid" method="post" action="IndividuServlet" style="padding-top: 0px ;">
                        <input type='file' name="imageenvoyee" id="imgInp" style="display: none"/>
                        <div class="row cells7">
                            <div class="cell padding5">
                                <label>Genre<span class="fg-red">*</span></label>
                                <div class="input-control select full-size" data-role="select" data-placeholder="Entrer le nom du bureau de destination">
                                    <select name="genre" class="js-select full-size" required="true">
                                        <option value="0" >Homme</option>
                                        <option value="1" ${individu.genre ? "":"selected"}>Femme</option>                            
                                    </select>
                                </div>
                            </div>
                            <div class="cell padding5 colspan2">
                                <label>Noms<span class="fg-red">*</span></label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="noms" type="text" value="${individu.nom}" required="true">
                                    <input id="imgblob" type="hidden" name="imgblob" value="">
                                    <input name="id" type="hidden" value="${individu.idindividu}" >
                                </div>
                            </div>
                            <div class="cell padding5 colspan2">
                                <label>Prénoms</label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="prenoms" type="text" value="${individu.prenom}" required="true">
                                </div>
                            </div>
                            <div class="cell padding5 colspan2">
                                <label>Surnom</label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="surnom" type="text" value="${individu.surnom}">
                                </div>
                            </div>
                        </div>
                        <div class="row cells4">
                            <div class="cell padding5">
                                <label>Région ou pays<span class="fg-red">*</span></label>
                                <div class="input-control select full-size" data-role="select" data-placeholder="Entrez le nom de la région ou du pays">
                                    <select name="region" class="js-select full-size" required="true">
                                        <option></option>
                                        <c:forEach items="${regions}" var="r">
                                            <option ${individu.region.idregion==r.idregion ? "selected":""} value="${r.idregion}">
                                                ${r.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="cell padding5">
                                <label>Qualité <span class="fg-red">*</span></label>
                                <div class="input-control select full-size" data-role="select" data-placeholder="Entrez la qualité">
                                    <select name="qualite" class="" required="true">
                                        <option></option>
                                        <c:forEach items="${qualites}" var="q">
                                            <option ${individu.qualite.idqualite==q.idqualite ? "selected":""} value="${q.idqualite}">
                                                ${q.libelle} .${q.code}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="cell padding5">
                                <label>Discipline</label>
                                <div id="mySelect2" class="input-control select full-size" style="padding-bottom: 10px" data-role="select" data-placeholder="Entrez la discipline">
                                    <select  name="disciplines" class="js-select full-size">
                                        <option></option>
                                        <c:forEach items="${disciplines}" var="d">
                                            <option value="${d.iddiscipline}" ${disciplineUtil.isInList(d, individu.individuDisciplines) ? "selected":""}>
                                                ${d.libelle} .${d.code} 
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="cell padding5">
                                <label id="categorielabel">
                                    Catégorie
                                    <span id="spinnerlabel" style="display: none" class="mif-spinner mif-ani-spin place-right"></span>
                                </label>
                                <div class="input-control select full-size" style="padding-bottom: 10px" data-role="select" data-placeholder="Entrez la discipline">
                                    <select name="categorie">


                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row cells5">
                            <div class="cell padding5">
                                <label>Date naiss.<span class="fg-red">*</span></label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="datenaiss" type="date" value="${individu.datenaiss}" required="true">
                                </div>
                            </div>
                            <div class="cell padding5 colspan2">
                                <label>Lieu de naissance<span class="fg-red">*</span></label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="lieunaiss" type="text" value="${individu.lieunaiss}">
                                </div>
                            </div>
                            <div class="cell padding5">
                                <label>Taille (cm)<span class="fg-red">*</span></label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="taille" type="number" value="${individu.taille}" required="true">
                                </div>
                            </div>
                            <div class="cell padding5">
                                <label>Poids (Kg)<span class="fg-red">*</span></label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="poids" type="number" value="${individu.poids}" required="true">
                                </div>
                            </div>

                        </div>
                        <div class="row cells4">
                            <div class="cell padding5 ">
                                <label>Télephone<span class="fg-red">*</span></label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="tel" type="text" value="${individu.tel}" required="true">
                                </div>
                            </div>
                            <div class="cell padding5 colspan2">
                                <label>Email</label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="email" type="text" value="${individu.email}">
                                </div>
                            </div>
                            <div class="cell padding5 ">
                                <label>Matricule</label>
                                <div class="input-control text full-size" data-role="input">
                                    <input name="matricule" type="text" value="${individu.matricule}" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="row cells6">
                            <div class="cell padding5 colspan6">
                                <br>
                                <input type="submit" value="Enregistrer" class="button fg-white bg-bleu" >
                                <c:if test="${!empty individu}">
                                    <button style="" type="submit" name="action" value="supprimer" class="button bg-or fg-white bd-white"> 
                                        Supprimer
                                    </button>
                                </c:if>


                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>



    </body>
</html>
