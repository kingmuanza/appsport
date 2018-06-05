package app.sport.servlets;

import app.sport.dao.QualiteDAO;
import app.sport.entities.Qualite;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "QualiteServlet", urlPatterns = {"/QualiteServlet"})
public class QualiteServlet extends HttpServlet {

    QualiteDAO qualiteDAO = new QualiteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("qualite", qualiteDAO.get(i));
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/qualite.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Qualite d = qualiteDAO.get(i);
            try {
                qualiteDAO.supprimer(d);
                option="option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");

            Qualite d = new Qualite();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = qualiteDAO.get(i);
            }

            d.setCode(code);
            d.setLibelle(libelle);

            if (id != null && !id.isEmpty()) {
                qualiteDAO.modifier(d);
            } else {
                qualiteDAO.ajouter(d);
            }
            option="option=true";

        }
        response.sendRedirect("start#!/qualites?"+option);
    }

}
