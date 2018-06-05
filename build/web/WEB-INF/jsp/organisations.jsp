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
        </style>
        <script>


        </script>
        <script>
            jQuery(document).ready(function ($) {

                $("#chargement").hide("slow");
                $(".nouveau").click(function (event) {
                    event.preventDefault();
                    console.log("clikaage"); 
                });



                var titre = "Liste des individus";
                $("#historique").DataTable({
                    'searching': true,
                    "iDisplayLength": 50,
                    "info": true,
                    dom: '<"top"fB>rt<"bottom"lp><"clear">',
                    buttons: [
                        {
                            text: "Nouveau",
                            title: titre,
                            message: '',
                            className: 'nouveau',
                            action : function(){
                                metroDialog.open('#dialog-organisation');
                            }
                        },
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
    </head>

    <body>
        <div class="grid condensed" style="margin: 0px;">

            <div id="idteste" class="fg-bleu" style="padding-top: 0px; font-size : 3em; font-weight: 200 ">
                <b>Organisations</b>                
            </div>
            <div id="affichageTableau" class="row cells4" style="padding-right: 20px; padding-top: 20px;">
                <table id="historique" class="dataTable" style="margin-bottom: 10px;">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Code</th>
                            <th>Libellé</th>
                            <th>Région</th>
                        </tr>
                    </thead>

                    <tfoot >
                        <tr>
                            <th>N°</th>
                            <th>Code</th>
                            <th>Libellé</th>
                            <th>Région</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach items="${organisations}" var="d" varStatus="loop">
                            <tr onclick="metroDialog.open('#dialog-organisation${d.idorganisation}');">
                                <td>${loop.count}</td>
                                <td>
                                    <span style="font-weight: 600; cursor: pointer">
                                        ${d.code}
                                    </span>
                                </td>
                                <td>${d.libelle}</td>
                                <td>${d.region.libelle}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>


        </div>



        <div data-role="dialog" id="dialog-organisation" class="bg-white padding20"
             data-close-button="true"
             data-windows-style="false"
             data-href="OrganisationServlet?temps=${temps}"
             data-overlay="true"
             data-color="fg-dark"
             data-width="600">
        </div>
        <c:forEach items="${organisations}" var="d">
            <div data-role="dialog" id="dialog-organisation${d.idorganisation}" class="bg-white padding20"
                 data-close-button="true"
                 data-windows-style="false"
                 data-href="OrganisationServlet?id=${d.idorganisation}&temps=${temps}"
                 data-overlay="true"
                 data-color="fg-dark"
                 data-width="600">
            </div>
        </c:forEach>
    </body>
</html>
