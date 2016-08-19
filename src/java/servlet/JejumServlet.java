/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.Jejum_Controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CARLOS
 */
@WebServlet("/jejum")
public class JejumServlet extends HttpServlet{

    public void chamaService (HttpServletRequest req, HttpServletResponse resp){
        try {
            service(req, resp);
        } catch (ServletException ex) {
            Logger.getLogger(JejumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JejumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List lista;
        Jejum_Controller jc = new Jejum_Controller();
        lista = jc.lista();
        
        req.setAttribute("jejum", lista);
        
        //RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/jejum.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("jejum.jsp");
        rd.forward(req, resp);
    }
    
    
}
