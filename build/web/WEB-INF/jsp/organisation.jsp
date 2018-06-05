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
            <div class="row ">
                <div class="cell  ">
                    <h1 class="fg-bleu">
                        <b>${empty organisation ? "Nouvelle organisation" : organisation.libelle}</b>
                    </h1>
                    <small class="">Les champs avec <span class="fg-red">*</span> sont obligatoires</small>
                </div>
            </div>
        </div>


        <form id="form1" runat="server" class="grid" method="post" action="OrganisationServlet" style="padding-top: 10px ;">
            <div class="row cells3">
                <div class="cell">
                    <label>Code <span class="fg-red">*</span></label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="code" type="text" value="${organisation.code}" required="true">
                        <input name="id" type="hidden" value="${organisation.idorganisation}" >
                    </div>
                </div>
                <div class="cell colspan2">
                    <label>Libellé <span class="fg-red">*</span></label>
                    <div class="input-control text full-size" data-role="input">
                        <input name="libelle" type="text" value="${organisation.libelle}" required="true">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="cell">
                    <label>Region <span class="fg-red">*</span></label>
                    <div class="input-control select full-size" data-role="input">
                        <select name="region" required>
                            <c:forEach items="${regions}" var="region">
                                <option value="${region.idregion}" ${region.idregion==categorie.region.idregion ? 'selected':''}>
                                    ${region.libelle}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row cells6">
                <div class="cell colspan6">
                    <input type="submit" value="Enregistrer" class="button fg-white bg-bleu" >
                    <c:if test="${!empty organisation}">
                        <button style="" type="submit" name="action" value="supprimer" class="button bg-or fg-white bd-white"> 
                            Supprimer
                        </button>
                    </c:if>


                </div>
            </div>
        </form>
    </body>
</html>
