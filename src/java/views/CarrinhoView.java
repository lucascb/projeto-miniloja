/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Carrinho;
import models.Loja;
import models.Produto;

/**
 *
 * @author lucas
 */
public class CarrinhoView {
    
    public static String mostrarCarrinho(Loja shop, Carrinho cart) {
        StringBuilder page = new StringBuilder();
        double precoTotal = 0;
        
        page.append("<!DOCTYPE html>"+"<html>"+"<head>"+"<title>"
                +"Miniloja"+"</title>"+"</head>"+"<body>");
        page.append("<h1>Seja bem-vindo, " + cart.getCliente() + "!</h1>");
        // Mostra a loja
        page.append("<h1>Nossos produtos</h1>" 
            + "<table>"
            + "<tr>" + "<th>ID</th>" + "<th>Produto</th>" + "<th>Preço</th>"
            + "</tr>");
        shop.getProdutos().forEach(prod -> {
            page.append("<tr>");
            page.append("<td>" + prod.getId() + "</td>");
            page.append("<td>" + prod.getDescription() + "</td>");
            page.append("<td>R$ " + prod.getPrice() + "</td>");
            page.append("</tr>");
        });
        // Mostra o carrinho
        page.append("</table><h1>Meu Carrinho</h1>");
        page.append("<table>");
        page.append("<tr>");
        page.append("<th>ID</th>");
        page.append("<th>Produto</th>");
        page.append("<th>Preço</th>");
        page.append("</tr>");
        for (Produto prod : cart.getProdutos()) {
            page.append("<tr>");
            page.append("<td align='center'>" + prod.getId() + "</td>");
            page.append("<td align='center'>" + prod.getDescription() + "</td>");
            page.append("<td align='center'>R$ " + prod.getPrice() + "</td>");
            page.append("</tr>");
            precoTotal += prod.getPrice();
        }
        page.append("</table>");
        page.append("<p><b>Subtotal:</b> R$ " + precoTotal +"</p>");
        // Forms
        page.append("<h2>Adicionar produto ao carrinho</h2>");
        page.append("<form action='/miniloja/comprar' method='POST'>"
            + "<input type='value' name='id' value='' />" 
            + "<input type='submit' value='Comprar' name='botaoComprar' />"
            + "<input type='hidden' name='op' value='add' />"
            + "</form>");
        page.append("<h2>Remover produto do carrinho</h2>");
        page.append("<form action='/miniloja/comprar' method='POST'>"
            + "<input type='value' name='id' value='' />" 
            + "<input type='submit' value='Remover' name='botaoRemover' />"   
            + "<input type='hidden' name='op' value='del' />"
            + "</form>");
        page.append("</body></html>");
        
        return page.toString();
    }
    
}
