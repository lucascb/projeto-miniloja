/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Carrinho;
import models.DaoLoja;
import models.Loja;
import models.Pessoa;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ComprasServlet", urlPatterns = {"/comprar"})
public class ComprasServlet extends HttpServlet {
    private Carrinho cart;
    private DaoLoja shop;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pessoa cliente = (Pessoa) request.getSession().getAttribute("cliente");
        cart = (Carrinho) request.getSession().getAttribute("carrinho");
        shop = new DaoLoja();
        
        if (cart == null) {
            cart = new Carrinho();
            request.getSession().setAttribute("carrinho", cart);
        }
        
        String comprar = request.getParameter("botaoComprar");
        // Usuario clicou para comprar algum item
        if (comprar != null) {
            String id = request.getParameter("idComprarProd");
            if (id != null) {
                int pid = Integer.parseInt(id);
                //System.out.println("Adicionado: " + pid);
                cart.addProduto(shop.getLoja().getProduto(pid));
            }
        }
        
        String remover = request.getParameter("botaoRemover");
        // Usuario clicou para remover algum item do carrinho
        if (remover != null) {
            String id = request.getParameter("idRemoverProd");
            if (id != null) {
                int pid = Integer.parseInt(id);
                //System.out.println("Removido: " + pid);
                cart.removeProduto(pid);
            }
        }
        
        // Redireciona para o carrinho
        request.getRequestDispatcher("carrinho.jsp").forward(request, response);
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
