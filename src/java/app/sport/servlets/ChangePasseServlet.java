package app.sport.servlets;

import app.sport.dao.UtilisateurDAO;
import app.sport.entities.Utilisateur;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChangePasseServlet", urlPatterns = {"/ChangePasseServlet"})
public class ChangePasseServlet extends HttpServlet {

    UtilisateurDAO utilisateurDAO = new UtilisateurDAO(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            
            int i = utilisateur.getIdutilisateur();
            request.setAttribute("u", utilisateurDAO.get(i));
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/changePasse.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            int i = utilisateur.getIdutilisateur();
            Utilisateur u = utilisateurDAO.get(i);
            String passe = request.getParameter("passe");
            String nouveau = request.getParameter("nouveau");
            String confirme = request.getParameter("confirme");
            if(nouveau.equals(confirme)){
                u.setPasse(nouveau);
                utilisateurDAO.modifier(u);
                response.sendRedirect("DeconnexionServlet");
            }else{
                response.sendRedirect("start?error=passe");
            }
            
        }

    }

}
