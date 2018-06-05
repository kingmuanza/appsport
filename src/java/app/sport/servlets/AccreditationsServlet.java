package app.sport.servlets;

import app.sport.dao.AccreditationDAO;
import app.sport.dao.CompetitionDAO;
import app.sport.entities.Competition;
import app.sport.entities.Utilisateur;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccreditationsServlet", urlPatterns = {"/AccreditationsServlet"})
public class AccreditationsServlet extends HttpServlet {

    AccreditationDAO accreditationDAO = new AccreditationDAO();
    CompetitionDAO competitionDAO = new CompetitionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {

            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Competition competition = competitionDAO.get(i);
                Date d = new Date();
                request.setAttribute("limite", d.after(competition.getDateFinAccreditation()));
                request.setAttribute("competition", competition);
                request.setAttribute("accreditations", accreditationDAO.getall(competition, utilisateur));
            } else {

                request.setAttribute("accreditations", accreditationDAO.getall(utilisateur));
            }
            request.setAttribute("temps", new Date().getTime());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accreditations.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
