package app.sport.servlets;

import app.sport.dao.AccreditationDAO;
import app.sport.dao.CompetitionDAO;
import app.sport.dao.IndividuDAO;
import app.sport.dao.StatutAccreditationDAO;
import app.sport.entities.Accreditation;
import app.sport.entities.Competition;
import app.sport.entities.Individu;
import app.sport.entities.StatutAccreditation;
import app.sport.entities.Utilisateur;
import app.sport.utils.BlobToString;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccreditationServlet", urlPatterns = {"/AccreditationServlet"})
public class AccreditationServlet extends HttpServlet {

    AccreditationDAO accreditationDAO = new AccreditationDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    CompetitionDAO competitionDAO = new CompetitionDAO();
    StatutAccreditationDAO statutAccreditationDAO = new StatutAccreditationDAO();
    BlobToString blobToString = new BlobToString();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String competition = request.getParameter("competition");
            Competition com = null;
            if (competition != null && !competition.isEmpty()) {
                int i = Integer.parseInt(competition);
                com = competitionDAO.get(i);
                request.setAttribute("competition", com);
            }
            List<Individu> individus =individuDAO.getAllNonAccredite(utilisateur, com);
            
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Accreditation accreditation = accreditationDAO.get(i);
                request.setAttribute("accreditation", accreditation);
                request.setAttribute("age", calculateAge(accreditation.getIndividu().getDatenaiss(), new Date()));
            } else {
                request.setAttribute("individus", individus);

            }
            request.setAttribute("statutAccreditations", statutAccreditationDAO.getall());
            request.setAttribute("blobToString", blobToString);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accreditation.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Accreditation d = accreditationDAO.get(i);
            try {
                accreditationDAO.supprimer(d);
                option = "option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            Accreditation d = new Accreditation();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = accreditationDAO.get(i);
            }

            String competition = request.getParameter("competition");
            Competition c = null;
            if (competition != null && !competition.isEmpty()) {
                int i = Integer.parseInt(competition);
                c = competitionDAO.get(i);
                d.setCompetition(c);
            }

            String statutAccreditation = request.getParameter("statutAccreditation");
            StatutAccreditation statut = null;
            if (statutAccreditation != null && !statutAccreditation.isEmpty()) {
                int i = Integer.parseInt(statutAccreditation);
                statut = statutAccreditationDAO.get(i);
                d.setStatutAccreditation(statut);
            } else {
                statut = statutAccreditationDAO.get(3);
                d.setStatutAccreditation(statut);
            }

            String individu = request.getParameter("individu");
            Individu indiv = null;
            if (individu != null && !individu.isEmpty()) {
                int i = Integer.parseInt(individu);
                indiv = individuDAO.get(i);
                d.setIndividu(indiv);
            }

            if (id != null && !id.isEmpty()) {
                accreditationDAO.modifier(d);
            } else {
                accreditationDAO.ajouter(d);
            }
            option = "option=true";

            response.sendRedirect("start#!/accreditations/" + d.getCompetition().getIdcompetition());
        }
    }
    
    public static int calculateAge(Date birthDate, Date currentDate) {
        LocalDate b = Instant.ofEpochMilli(birthDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate c = Instant.ofEpochMilli(currentDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        
        if ((b != null) && (c != null)) {
            return Period.between(b, c).getYears();
        } else {
            return 0;
        }
    }

}
