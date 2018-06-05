package app.sport.servlets;

import app.sport.dao.DisciplineDAO;
import app.sport.entities.Discipline;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisciplineServlet", urlPatterns = {"/DisciplineServlet"})
public class DisciplineServlet extends HttpServlet {

    DisciplineDAO disciplineDAO = new DisciplineDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("discipline", disciplineDAO.get(i));
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/discipline.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Discipline d = disciplineDAO.get(i);
            try {
                disciplineDAO.supprimer(d);
                option="option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");

            Discipline d = new Discipline();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = disciplineDAO.get(i);
            }

            d.setCode(code);
            d.setLibelle(libelle);

            if (id != null && !id.isEmpty()) {
                disciplineDAO.modifier(d);
            } else {
                disciplineDAO.ajouter(d);
            }
            option="option=true";

        }
        response.sendRedirect("start#!/disciplines?"+option);
    }

}
