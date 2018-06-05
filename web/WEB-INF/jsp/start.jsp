<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App's SPORT</title>
        <link href="css/metro.css?id=jdjdjd" rel="stylesheet" type="text/css"/>
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
        <script src="js/select2.full.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <style>
            input[type=text].rechercher {
                border: 0px solid #444;                
            }

            input[type=text]:focus {
                border-bottom: 1px solid #ccc;
            }

            input[type=text].muanza {
                width: 100%;
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
                opacity: 0.9;
            }
            .menu_muanza:hover a{
                font-weight: 400;
                opacity: 0.9;
            }
            span#labelTableau:hover{
                color:#555;
            }
            span#labelCarte:hover{
                color:#555;
            }

            .gras{
                font-weight: bold!important;
                color:#f09433;
                
            }
            .instagram{
                background: #f09433; 
                background: -moz-linear-gradient(45deg, #f09433 0%, #e6683c 25%, #dc2743 50%, #cc2366 75%, #bc1888 100%); 
                background: -webkit-linear-gradient(45deg, #f09433 0%,#e6683c 25%,#dc2743 50%,#cc2366 75%,#bc1888 100%); 
                background: linear-gradient(45deg, #f09433 0%,#e6683c 25%,#dc2743 50%,#cc2366 75%,#bc1888 100%); 
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f09433', endColorstr='#bc1888',GradientType=1 );
            }

            .arriereplanflat{
                background: none !important;
                background-image: url("img/wallbw.jpg") !important;
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% auto !important;
            }

            .fg-bleu{
                color: #1b4e9c;
            }
            .bg-bleu{
                background-color: #1b4e9c;
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
                border: 0px solid #1b4e9c;
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
            a.dt-button{
                background: none !important;
                border: none !important;
                border-radius: 0;
                background-color: #f09433 !important;
                color :#fff !important;
            }
            a.paginate_button.current{
                background: none !important;
                border: none !important;
                border-radius: 0;
                background-color: #f09433 !important;
                color :#fff !important;
            }
            .menu_muanza a{
                font-weight: bold!important;
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
        </script>
        <script>
            jQuery(document).ready(function ($) {
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

        </script>

        <script>
            var app = angular.module("myApp", ["ngRoute"]);
            app.config(function ($routeProvider) {
                $routeProvider
                        .when("/", {
                            templateUrl: "IndividusServlet"
                        })
                        .when("/individus", {
                            templateUrl: "IndividusServlet"
                        })
                        .when("/competitions", {
                            templateUrl: "CompetitionsServlet"
                        })
                        .when("/accreditations", {
                            templateUrl: "AccreditationsServlet"
                        })
                        .when("/accreditations/:id", {
                            templateUrl: function (params) {
                                return "AccreditationsServlet?id="+params.id
                            }
                        })
                        .when("/voir/:id", {
                            templateUrl: function (params) {
                                return "IndividuVoirServlet?id="+params.id
                            }
                        })
                        .when("/qualites", {
                            templateUrl: "QualitesServlet"
                        })
                        .when("/regions", {
                            templateUrl: "RegionsServlet"
                        })
                        .when("/disciplines", {
                            templateUrl: "DisciplinesServlet"
                        })
                        .when("/utilisateurs", {
                            templateUrl: "UtilisateursServlet"
                        })
                        .when("/categories", {
                            templateUrl: "CategoriesServlet"
                        })
                        .when("/organisations", {
                            templateUrl: "OrganisationsServlet"
                        })
                        .when("/recherche/:nom?/:region?/:qualite?/:discipline?/:genre?", {
                            templateUrl: function (params) {
                                var nom = "";
                                if (params.nom) {
                                    nom = params.nom;
                                }
                                var region = "";
                                if (params.region) {
                                    region = params.region;
                                }
                                var qualite = "";
                                if (params.qualite) {
                                    qualite = params.qualite;
                                }
                                var discipline = "";
                                if (params.discipline) {
                                    discipline = params.discipline;
                                }
                                var genre = "";
                                if (params.genre) {
                                    genre = params.genre;
                                }

                                return "recherche?nom=" + nom + "&region=" + region + "&qualite=" + qualite + "&discipline=" + discipline + "&genre=" + genre;
                            }
                        })
                        .when("/blue", {
                            templateUrl: "blue.htm"
                        });
            });
        </script>
    </head>

    <body ng-app="myApp" class="arriereplanflat">
        <div style="position: fixed; top : 0; right: 55px;" class="bg-or" >
            <div class="padding20 fg-white" style="text-align: right">
                <b style="font-size: 1.2em">${sessionScope.utilisateur.noms} ${sessionScope.utilisateur.prenoms}</b><br>
                <c:if test="${sessionScope.utilisateur.utilisateurProfil.idutilisateurProfil!=1}">
                ${sessionScope.utilisateur.organisation.libelle}<br>
                    
                </c:if>
                <c:if test="${sessionScope.utilisateur.utilisateurProfil.idutilisateurProfil==1}">
                <div style="padding-top: 5px;" class="fg-bleu" >
                    <b>${sessionScope.utilisateur.utilisateurProfil.libelle}</b>
                </div>
                    
                </c:if>
            </div>
        </div>
        <div data-role="charm" data-position="left" id="specific-charms" style="padding: 0;background-image: url('img/oui.jpg'); background-size: 100% auto; height: 100vh; width: 25vw; border-right: 0px solid #dc2743;overflow: hidden;">
            <div>
                <div style=" height:100vh; overflow: hidden;">
                    <div style="height:100vh; background: linear-gradient(to right, #000046, #1cb5e0); opacity: 0.7">
                        <div style="height:100vh;" class="bg-darkBlue"></div>
                    </div>
                    <div class="padding20" style="width: 100%; padding-left: 20px; height:100vh; position: absolute; top: 0">
                        <div class="instagram" style="width: 15vh; border-radius: 50%; padding: 4px">
                            <img src="img/user.png" alt="" style="border: 3px solid white;width: 15vh; border-radius: 50%;"/>

                        </div>
                        <div style="font-size: 1.8em; padding-top:5px; color: white; font-weight: bold!important;">
                            ${sessionScope.utilisateur.noms} ${sessionScope.utilisateur.prenoms}
                        </div>
                        <div style="padding-top:5px;font-size: 1.2em;color:#f09433;font-weight: bold!important;">
                            <span>${sessionScope.utilisateur.organisation.libelle}</span>
                        </div>

                        <div class="row fg-white" style="width: 100%">
                            <div class="cell" style="padding-top: 20px">
                                <div class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 20px; cursor: pointer">
                                    <a class="fg-white" href="#!individus">
                                        Individus 
                                        <span class="place-right gras">${individus}</span>
                                    </a>
                                </div>
                                <div class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; cursor: pointer">
                                    <a class="fg-white" href="#!competitions">
                                        Compétitions 
                                        <span class="place-right gras">${competitions}</span>
                                    </a>
                                </div>
                                <c:if test="${sessionScope.utilisateur.utilisateurProfil.idutilisateurProfil==1}">


                                    <div  class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!disciplines">
                                            Disciplines 
                                            <span class="place-right gras">${disciplines}</span>
                                        </a>
                                    </div>
                                    <div  class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!categories">
                                            Catégories 
                                            <span class="place-right gras">${categories}</span>
                                        </a>
                                    </div>
                                    <div  class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!qualites">
                                            Qualités 
                                            <span class="place-right gras">${qualites}</span>
                                        </a>
                                    </div>
                                    <div  class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!regions">
                                            Régions ou pays 
                                            <span class="place-right gras">${regions}</span>
                                        </a>
                                    </div>
                                    <div  class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!organisations">
                                            Organisations 
                                            <span class="place-right gras">${organisations}</span>
                                        </a>
                                    </div>
                                    <div  class="menu_muanza" style="font-size:1.5em ; font-weight: 200; padding-top: 10px; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!utilisateurs">
                                            Utilisateurs 
                                            <span class="place-right gras">${utilisateurs}</span>
                                        </a>
                                    </div>
                                    <!--div  class="menu_muanza" style="padding-top: 40px; font-size:1.5em ; font-weight: 200; padding-bottom: 5px;">
                                        <a class="fg-white" href="#!parametres">Paramètres</a>
                                    </div-->
                                </c:if>
                                <div style="font-size:1.5em ; font-weight: 200; padding-bottom: 5px; padding-top: 40px;">
                                    <a href="DeconnexionServlet" style="color:#f09433">
                                        <b>Déconnexion</b>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="grid condensed" style="margin: 0px;">
            <div class="row cells12" > 
                <div class="cell padding5 bg-bleu" style="text-align: center;">
                    <h1 onclick="toggleMetroCharm('#specific-charms');" style="cursor: pointer; z-index: 151881">
                        <span class="mif-menu fg-white" ></span>
                    </h1>
                </div>
                <div class="cell colspan11" style="height: 100vh ; margin-top: 0px; padding-top: 15px; padding-right: 0px; overflow-y: scroll">
                    <h5 class="fg-red place-right padding30">
                        ${empty error ? "":"Les mots de passe ne sont pas identiques"}
                    </h5>
                    <div ng-view style="padding : 20px; padding-left: 30px;"></div>
                    <!--div style="position: fixed; bottom:0; width: 100%; background: linear-gradient(to right, #000046, #1cb5e0);">
                        <div style="padding :7px;font-size: 1.0em;color:wheat; text-align: center">
                            <span>Date limite des enregistrements : le 20/08/2018</span>
                        </div>
                    </div-->
                    <div id="chargement" style="top: 50%; left: 50%;height: 200px;transform: translate(-50%, -50%);position: relative;">
                        <div class="align-center fg-grayLight" style="margin: 0;position: absolute;top: 50%; left: 50%;height: 200px;transform: translate(-50%, -50%);">
                            <div style="font-size: 7em" class="mif-spinner mif-ani-spin mif-4x"></div>
                            <div class="" style="font-size: 2.1em; font-weight: 100; padding-top: 15px;" >Chargement</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-role="dialog" id="dialog-passe" class="bg-white padding20"
             data-close-button="true"
             data-windows-style="false"
             data-href="ChangePasseServlet"
             data-overlay="true"
             data-color="fg-dark"
             data-width="600">
        </div>
    </body>
</html>
