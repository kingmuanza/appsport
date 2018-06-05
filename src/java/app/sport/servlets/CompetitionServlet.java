package app.sport.servlets;

import app.sport.dao.CompetitionDAO;
import app.sport.entities.Competition;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CompetitionServlet", urlPatterns = {"/CompetitionServlet"})
public class CompetitionServlet extends HttpServlet {

    CompetitionDAO competitionDAO = new CompetitionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("competition", competitionDAO.get(i));
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/competition.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Competition d = competitionDAO.get(i);
            try {
                competitionDAO.supprimer(d);
                option="option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            String debut = request.getParameter("debut");
            System.out.println("Date debut : "+debut);
            String fin = request.getParameter("fin");
            System.out.println("Date fin : "+fin);
            Date dateDebut = null;
            Date dateFin = null;
            try {
                dateDebut = sdf.parse(debut);
                //Fin Paramètres
            } catch (ParseException ex) {
            }
            try {
                dateFin = sdf.parse(fin);
                //Fin Paramètres
            } catch (ParseException ex) {
            }
            String debutaccr = request.getParameter("debutaccr");
            System.out.println("Date debutaccr : "+debutaccr);
            String finaccr = request.getParameter("finaccr");
            System.out.println("Date finaccr : "+finaccr);
            Date dateDebutaccr = null;
            Date dateFinaccr = null;
            try {
                dateDebutaccr = sdf.parse(debutaccr);
                //Fin Paramètres
            } catch (ParseException ex) {
            }
            try {
                dateFinaccr = sdf.parse(finaccr);
                //Fin Paramètres
            } catch (ParseException ex) {
            }

            Competition d = new Competition();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = competitionDAO.get(i);
            }

            d.setCode(code);
            d.setLibelle(libelle);
            d.setDateDebut(dateDebut);
            d.setDateFin(dateFin);
            d.setDateDebutAccreditation(dateDebutaccr);
            d.setDateFinAccreditation(dateFinaccr);

            if (id != null && !id.isEmpty()) {
                competitionDAO.modifier(d);
            } else {
                competitionDAO.ajouter(d);
            }
            option="option=true";

        }
        response.sendRedirect("start#!/competitions?"+option);
    }

}
