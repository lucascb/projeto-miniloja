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
import views.CarrinhoView;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ComprasServlet", urlPatterns = {"/comprar"})
public class ComprasServlet extends HttpServlet {
    private Carrinho cart;
    private DaoLoja shop;
    private String user;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pessoa cliente = (Pessoa) request.getSession().getAttribute("cliente");
        cart = (Carrinho) request.getSession().getAttribute("carrinho");
        shop = new DaoLoja();
        
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("email")) {
                user = c.getValue();
            }
        }
       
        if (user == null) {
            user = request.getParameter("email");
            Cookie c = new Cookie("email", user);
            c.setMaxAge(1000 * 24 * 3600 * 30); // 30 dias
            response.addCookie(c);
        }
        
        if (cart == null) {
            cart = new Carrinho(user);
            request.getSession().setAttribute("carrinho", cart);
        }
        
        String operacao = request.getParameter("op");
        if (request.getParameter("id") != null) {
            if (operacao.equals("add")) {
                int pid = Integer.parseInt(request.getParameter("id"));
                cart.addProduto(shop.getLoja().getProduto(pid));
            } else if (operacao.equals("del")) {
                int pid = Integer.parseInt(request.getParameter("id"));
                cart.removeProduto(pid);
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(CarrinhoView.mostrarCarrinho(shop.getLoja(), cart));
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
