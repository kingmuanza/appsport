package app.sport.servlets;

import app.sport.dao.QualiteDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="QualitesServlet", urlPatterns={"/QualitesServlet"})
public class QualitesServlet extends HttpServlet {
   
    QualiteDAO qualiteDAO = new QualiteDAO(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("qualites", qualiteDAO.getall());
        request.setAttribute("temps", new Date().getTime());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/qualites.jsp").forward(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
