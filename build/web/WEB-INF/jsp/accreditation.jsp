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


        </script>

        <style>
            input[type=text].rechercher {
                border: 0px solid #444;                
            }

            input[type=text]:focus {
                border-bottom: 1px solid #ccc;
            }

            input[type=text].muanza {
                width: 25%;
                padding: 10px 10px;
                margin: 5px 0;
                display: inline-block;
                border: 2px solid #ccc;
                border-radius: 0px;
                box-sizing: border-box;
            }
            select.muanza {
                width: 15%;
                padding: 10px 10px;
                margin: 5px 0;
                display: inline-block;
                border: 0px solid #ccc;
                border-radius: 0px;
                box-sizing: border-box;
            }

            img.muan:hover{
                transform: scale(0.95);
                opacity: 0.8;
                cursor: pointer;
            }

            .menu_muanza:hover {
                cursor: pointer;
            }
            .menu_muanza:hover span{
                font-weight: 400;
                color:#555;
            }
            span#labelTableau:hover{
                color:#555;
            }
            span#labelCarte:hover{
                color:#555;
            }
        </style>
    </head>
    <body >
        <div class="grid">
            <c:if test="${!empty accreditation}">
                <div class="row cells5">
                    <div class="cell " id="cadre" style="padding: 1px; border: 1px solid #f09433 ;border-radius: 0px ; min-height: 40px;">
                        <img  id="blah" src="${empty accreditation.individu.individuPhoto.src ? 'img/user.png': blobToString.generate(accreditation.individu.individuPhoto.src)}" class="bd-white muan" style="border: 2px solid ">
                    </div>
                    <div class="cell colspan4">
                        <div class="fg-bleu" style="font-size: 2.5em; padding-bottom: 10px;">
                            <b>
                                ${empty accreditation.individu ? "Nouvel" : accreditation.individu.nom} 
                            ${empty accreditation.individu ? "individu": accreditation.individu.prenom},
                            <span class="fg-or">${age} ans</span>
                            </b>
                        </div>
                        <div style="font-size: 1.2em; padding-bottom: 5px;">
                            <b class="fg-bleu">Organisation</b> :
                            ${empty accreditation.individu.organisation ? "Aucune Organisation": accreditation.individu.organisation.libelle} 
                        
                            <b class="fg-bleu">Région</b> :
                            ${empty accreditation.individu.region ? "Aucune région": accreditation.individu.region.libelle}
                        
                            <b class="fg-bleu">Qualité</b> :
                            ${empty accreditation.individu.qualite ? "Aucune région": accreditation.individu.qualite.libelle}
                        
                            <b class="fg-bleu">Taille</b> :
                            ${empty accreditation.individu.taille ? "0": accreditation.individu.taille} cm
                        
                            <b class="fg-bleu">Poids</b> :
                            ${empty accreditation.individu.poids ? "0": accreditation.individu.poids} Kg
                        </div>
                        <div class="fg-bleu" style="font-size: 1.2em; padding-bottom: 5px;">
                        </div>

                    </div>
                </div>
            </c:if>
            <c:if test="${empty accreditation}">
                <div class="fg-bleu" style="padding-top: 0px; font-size : 3em; font-weight: 200 ">
                    <b>Demande d'accréditation</b>
                </div>
            </c:if>

        </div>


        <form id="form1" runat="server" class="grid" method="post" action="AccreditationServlet" style="padding-top: 10px ;">

            <div class="row">
                <div class="cell">
                    <label>Compétition <span class="fg-red">*</span></label>
                    <div class="input-control select full-size" data-role="input">
                        <select name="competition" required readonly>
                            <option value="${competition.idcompetition}" selected>
                                ${competition.libelle}
                            </option>
                        </select>
                    </div>
                </div>
            </div>
            <c:if test="${!empty accreditation}">

                <div class="row">
                    <div class="cell">
                        <label>Statut de l'accréditation <span class="fg-red">*</span></label>
                        <div class="input-control select full-size" data-role="input">
                            <input name="id" type="hidden" value="${accreditation.idaccreditation}" >
                            <select name="statutAccreditation" required>
                                <c:forEach items="${statutAccreditations}" var="statutAccreditation">
                                    <option value="${statutAccreditation.idstatutAccreditation}" ${statutAccreditation.idstatutAccreditation==accreditation.statutAccreditation.idstatutAccreditation ? 'selected':''}>
                                        ${statutAccreditation.libelle}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty accreditation}">

                <div class="row">
                    <div class="cell">
                        <label>Individu <span class="fg-red">*</span></label>
                        <div class="input-control select full-size" data-role="input">
                            <select name="individu" required>
                                <c:forEach items="${individus}" var="individu">
                                    <option value="${individu.idindividu}">
                                        ${individu.nom} ${individu.prenom}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row cells6">
                <div class="cell colspan6">
                    <input type="submit" 
                           value="${!empty accreditation ? 'Sauvegarder l\'accrédiation':'Demander l\'accrédiation'}" 
                           class="button fg-white bg-bleu" >

                </div>
            </div>
        </form>
    </body>
</html>
