package app.sport.servlets;

import app.sport.dao.UtilisateurDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="UtilisateursServlet", urlPatterns={"/UtilisateursServlet"})
public class UtilisateursServlet extends HttpServlet {
   
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("temps", new Date().getTime());
        request.setAttribute("utilisateurs", utilisateurDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/utilisateurs.jsp").forward(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
