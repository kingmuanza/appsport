<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App's SPORT</title>
        <link href="css/metro.css?id=6656565" rel="stylesheet" type="text/css"/>
        <link href="css/metro-schemes.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="css/metro-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="css/keama.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2.css" rel="stylesheet" type="text/css"/>

        <script src="js/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script src="js/metro.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <style>
            input[type=text].rechercher {
                border: 4px solid #444;                
            }

            ::placeholder { /* Most modern browsers support this now. */
                font-weight: 200;
            }

            input[type=text].muanza:focus {
                width: 100%;
                padding: 10px 10px;
                margin: 5px 0;
                display: inline-block;
                border: 2px solid #f09433;
                border-radius: 0px;
                box-sizing: border-box;
            }

            input[type=text].muanza {
                width: 100%;
                padding: 10px 10px;
                margin: 5px 0;
                display: inline-block;
                border: 2px solid #f09433;
                border-radius: 0px;
                box-sizing: border-box;
            }
            select.muanza {
                width: 19.25%;
                padding: 10px 10px;
                margin: 5px 0;
                display: inline-block;
                border: 2px solid #ccc;
                border-radius: 0px;
                box-sizing: border-box;
            }

            img.muanza:hover{
                transform: scale(1.2);
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


            table{
                border: 0px solid #1b4e9c;
            }
            td {
                border: 0px solid #1b4e9c;
            }

            th, td {
                padding: 2px;
                text-align: left;
            }
            th {
                background-color: #1b4e9c;
                color: white !important;
            }
            tr{
                background: none !important;
            }
            tr:nth-child(even) {
                background-color: #f0f0fa !important;
            }
            tr:hover {
                background-color: #f09433!important;
                cursor: pointer;
            }
            .impression{
                background-color: #f09433;
                color :#fff;
            }

        </style>
        <script>
            function affichageTableau() {
                $("#affichageCarreau").hide("slow");
                $("#affichageTableau").css("display", "block");
                $("#labelTableau").css("font-weight", "400");
                $("#labelCarte").css("font-weight", "200");
            }
            function affichageCarte() {
                $("#affichageTableau").css("display", "none");
                $("#affichageCarreau").show("slow");
                $("#labelTableau").css("font-weight", "200");
                $("#labelCarte").css("font-weight", "400");

            }

            function reinit() {
                window.location.href = "start#";
            }
            function rechercheAvancee() {
                var nom = $('input[name=nom]').val();
                console.log("Nom : " + nom);
                var region = $('select[name=region]').val();
                console.log("region : " + region);
                var qualite = $('select[name=qualite]').val();
                console.log("qualite : " + qualite);
                var discipline = $('select[name=discipline]').val();
                console.log("discipline : " + discipline);
                var genre = $('select[name=genre]').val();
                console.log("genre : " + genre);

                window.location.href = "start#!/recherche/" + nom + "/" + region + "/" + qualite + "/" + discipline + "/" + genre;
            }
        </script>
        <script>
            jQuery(document).ready(function ($) {

                $("#chargement").hide("slow");


                var titre = "Liste des individus";
                $("#historique").DataTable({
                    'searching': true,
                    "iDisplayLength": 50,
                    "info": true,
                    dom: '<"top"fB>rt<"bottom"lp><"clear">',
                    "order": [[0, "desc"], [1, 'desc']],
                    buttons: [
                        {
                            extend: 'excel',
                            text: "Exporter vers Excel",
                            title: titre,
                            message: '',
                            className: 'impressionExcel'
                        },
                        {
                            extend: 'pdfHtml5',
                            text: "Exporter en PDF",
                            title: titre,
                            message: '',
                            className: 'impressionPDF ribbed-grayLighter bd-white'
                        },
                        {
                            extend: 'print',
                            text: "Imprimer",
                            title: titre,
                            message: '',
                            className: 'impression'
                        }
                    ],
                    "language": {
                        "sEmptyTable": "Aucune donnée disponible",
                        "sInfo": "Affiche _START_ à _END_ sur _TOTAL_ entrées",
                        "sLengthMenu": "Afficher _MENU_ lignes par page",
                        "sSearch": "Rechercher : ",
                        "zeroRecords": "Aucun résultat",
                        "info": "Page _PAGE_ sur _PAGES_",
                        "infoEmpty": "Aucun résultat disponible",
                        "sProcessing": "Veuillez patienter...",
                        "infoFiltered": "(sur les _MAX_ disponibles)",
                        "paginate": {
                            "previous": "Précédent",
                            "next": "Suivant"
                        }
                    }
                });
            });

            $("#mySelect23").focusout(function () {
                var id = $("select[name=discipline]").val();
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

    <body>
        <div class="grid condensed" style="margin: 0px;">
            <span class="fg-bleu" style="margin-top: -50px; font-size : 3em; font-weight: 200 ">
                <b>Individus</b>
                <span onclick="metroDialog.open('#dialog-profil')" style="cursor: pointer" class="mif-user-plus fg-bleu"></span>
            </span>
            <div style="padding-top: 10px; padding-right: 0px" >
                <div style="padding-right: 15px">
                    <input name="nom" class="muanza" value="${nom}" type="text" placeholder="Rechercher un individu par nom, prénom et matricule">
                </div>
                <div>
                    <select class="muanza" name="region" class="js-select">
                        <option value="">Région ou pays</option>
                        <c:forEach items="${regions}" var="r">
                            <option ${region.idregion==r.idregion ? "selected":""} value="${r.idregion}">
                                ${r.libelle}
                            </option>
                        </c:forEach>
                    </select>
                    <select class="muanza" name="qualite" class="js-select">
                        <option value="">Qualité</option>
                        <c:forEach items="${qualites}" var="q">
                            <option ${qualite.idqualite==q.idqualite ? "selected":""} value="${q.idqualite}">
                                ${q.libelle} .${q.code}
                            </option>
                        </c:forEach>
                    </select>
                    <select id="mySelect23" class="muanza" name="discipline" class="js-select" >
                        <option value="">Discipline</option>
                        <c:forEach items="${disciplines}" var="d">
                            <option ${discipline.iddiscipline==d.iddiscipline ? "selected":""} value="${d.iddiscipline}">
                                ${d.libelle} .${d.code} 
                            </option>
                        </c:forEach>
                    </select>
                    <select class="muanza" name="categorie">
                        <option value="">Categorie</option>
                        <c:forEach items="${categories}" var="c">
                            <option ${categorie.idcategorie==c.idcategorie ? "selected":""} value="${c.idcategorie}">
                                ${c.libelle} .${c.code} 
                            </option>
                        </c:forEach>
                    </select>
                    <select class="muanza" name="genre" class="js-select">
                        <option value="">Genre</option>
                        <option ${genre=="0" ? "selected":""} value="0">Homme</option>
                        <option ${genre=="1" ? "selected":""} value="1">Femme</option>
                    </select>

                </div>
            </div>

            <div style="padding-top: 10px; padding-bottom: 20px; padding-right: 15px;">
                <button onclick="rechercheAvancee();" class="button fg-white bg-bleu" style="background-color: #1b4e9c">
                    Reinitialiser
                </button>
                <button onclick="rechercheAvancee();" class="button fg-white bg-bleu" style="background-color: #1b4e9c">
                    Rechercher
                </button>
            </div>
            <div id="affichageTableau" class="row" style="padding-top: 20px;">
                <table id="historique" class="dataTable " style="margin-bottom: 10px; width: 100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>G</th>
                            <th>Matricule</th>
                            <th>Noms</th>
                            <th>Prenoms</th>
                            <th>Région</th>
                            <th>Qualité</th>
                            <th>Disciplines</th>
                            <th>Date naiss.</th>
                            <th>Taille</th>
                            <th>Poids</th>
                        </tr>
                    </thead>

                    <tfoot >
                        <tr>
                            <th>ID</th>
                            <th>G</th>
                            <th>Matricule</th>
                            <th>Noms</th>
                            <th>Prenoms</th>
                            <th>Région</th>
                            <th>Qualité</th>
                            <th>Disciplines</th>
                            <th>Date naiss.</th>
                            <th>Taille</th>
                            <th>Poids</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach items="${individus}" var="i">
                            <tr>
                                <td>${i.idindividu}</td>
                                <td>${i.genre ? 'H':'F'}</td>
                                <td>
                                    <span style="font-weight: 600; cursor: pointer" onclick="metroDialog.open('#dialog-profil${i.idindividu}')">
                                        ${i.matricule}
                                    </span>
                                </td>
                                <td>
                                    <span style="font-weight: 600; cursor: pointer" onclick="metroDialog.open('#dialog-profil${i.idindividu}')">
                                        ${i.nom}
                                    </span>
                                </td>
                                <td>${i.prenom}</td>
                                <td>${i.region.code}</td>
                                <td>${i.qualite.code}</a></td>
                                <td>
                                    <c:forEach items="${i.individuDisciplines}" var="id">
                                        <span style="padding-right: 10px;">${id.discipline.libelle}</span>
                                    </c:forEach> 
                                </td>
                                <td>${i.datenaiss}</td>
                                <td>${i.poids}</td>
                                <td>${i.taille}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>


        </div>



        <div data-role="dialog" id="dialog-profil" class="bg-white padding20"
             data-close-button="true"
             data-windows-style="false"
             data-href="IndividuServlet?temps=${temps}"
             data-overlay="true"
             data-color="fg-dark"
             data-overlay-color="overlay-bleu"
             data-width="800"
             style="border : 2px solid #1b4e9c">
        </div>
        <c:forEach items="${individus}" var="p">
            <div data-role="dialog" id="dialog-profil${p.idindividu}" class="bg-white padding20"
                 data-close-button="true"
                 data-windows-style="false"
                 data-href="IndividuServlet?id=${p.idindividu}&temps=${temps}"
                 data-overlay="true"
                 data-color="fg-dark"
                 data-overlay-color="overlay-bleu"
                 data-width="800"
                 style="border : 2px solid #1b4e9c">
            </div>
        </c:forEach>
    </body>
</html>
