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

/**
 *
 * @author lucas
 */
@WebServlet(name = "AdminLojaServlet", urlPatterns = {"/admin"})
public class AdminLojaServlet extends HttpServlet {
    private DaoLoja shop;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Carrega os produtos da loja
        if (shop == null) {
            shop = new DaoLoja();
        }
        
        // Adiciona o produto
        String desc = request.getParameter("description");
        String price = request.getParameter("price");
        if (desc != null && price != null) {
            this.shop.addProduto(desc, Double.parseDouble(price));
        }
        
        // Remove um produto
        String remover = request.getParameter("botaoRemover");
        if (remover != null) {
            System.out.println(remover);
            String id = request.getParameter("idRemoverProd");
            if (id != null) {
                System.out.println("Produto removido: " + id);
                int pid = Integer.parseInt(id);
                this.shop.removeProduto(pid);
            }
        }
        
        // Redireciona para a loja
        request.getRequestDispatcher("loja.jsp").forward(request, response);
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
