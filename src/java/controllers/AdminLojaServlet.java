/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Carrinho;
import models.DaoLoja;
import models.Loja;
import models.Pessoa;
import models.Produto;
import views.LojaView;

/**
 *
 * @author lucas
 */
@WebServlet(name = "AdminLojaServlet", urlPatterns = {"/admin"})
public class AdminLojaServlet extends HttpServlet {
    private DaoLoja shop;
    
    @Override
    public void init() throws ServletException { 
        if (shop == null) {
            try {
                shop = new DaoLoja();
            } catch (IOException ex) {
                Logger.getLogger(AdminLojaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("description") != null) {
            String desc = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
        
            this.shop.addProduto(desc, price);
        }
        
        response.setContentType("text/html;charset=UTF-8");      
        try (PrintWriter out = response.getWriter()) {
            out.print(LojaView.mostrarProdutos(this.shop.getLoja()));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
