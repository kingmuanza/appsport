package app.sport.servlets;

import app.sport.dao.UtilisateurDAO;
import app.sport.entities.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="ConnexionServlet", urlPatterns={"/ConnexionServlet"})
public class ConnexionServlet extends HttpServlet {
    
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        //System.out.println(httpSession.getAttribute("error"));
        String login = request.getParameter("login");
        String passe = request.getParameter("passe");
        Utilisateur utilisateur = null;
        utilisateur = utilisateurDAO.get(login, passe);
        if (utilisateur != null) {
            httpSession.setAttribute("utilisateur", utilisateur);
            httpSession.setAttribute("error", null);
            httpSession.removeAttribute("error");
            response.sendRedirect("start");
        } else {
            httpSession.setAttribute("error", "Login ou mot de passe incorrect");
            response.sendRedirect("index.htm");
        }

    }

    
}
