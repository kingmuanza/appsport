package app.sport.servlets;

import app.sport.dao.RegionDAO;
import app.sport.entities.Region;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegionServlet", urlPatterns = {"/RegionServlet"})
public class RegionServlet extends HttpServlet {

    RegionDAO regionDAO = new RegionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("region", regionDAO.get(i));
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/region.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Region d = regionDAO.get(i);
            try {
                regionDAO.supprimer(d);
                option="option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");

            Region d = new Region();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = regionDAO.get(i);
            }

            d.setCode(code);
            d.setLibelle(libelle);

            if (id != null && !id.isEmpty()) {
                regionDAO.modifier(d);
            } else {
                regionDAO.ajouter(d);
            }
            option="option=true";

        }
        response.sendRedirect("start#!/regions?"+option);
    }

}
