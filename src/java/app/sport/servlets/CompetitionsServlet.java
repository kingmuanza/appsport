package app.sport.servlets;

import app.sport.dao.CompetitionDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CompetitionsServlet", urlPatterns={"/CompetitionsServlet"})
public class CompetitionsServlet extends HttpServlet {
   
    CompetitionDAO competitionDAO = new CompetitionDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("competitions", competitionDAO.getall());
        request.setAttribute("temps", new Date().getTime());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/competitions.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
