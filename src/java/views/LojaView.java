/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Loja;
import models.Produto;

/**
 *
 * @author lucas
 */
public class LojaView {
    
    public static String mostrarProdutos(Loja shop) {
        StringBuilder page = new StringBuilder();
        
        page.append("<!DOCTYPE html>" + "<html>" + "<head>" 
            + "<title>Miniloja Admin</title>"
            + "</head>" + "<body>" + "<h1 align='center'>Produtos cadastrados</h1>" 
            + "<table style=\"width:100%\">"
            + "<tr>" + "<th>ID</th>" + "<th>Produto</th>" + "<th>Preço</th>"
            + "</tr>");
        shop.getProdutos().forEach(prod -> {
            page.append("<tr>");
            page.append("<td align='center'>" + prod.getId() + "</td>");
            page.append("<td align='center'>" + prod.getDescription() + "</td>");
            page.append("<td align='center'>" + prod.getPrice() + "</td>");
            page.append("</tr>");
        });
        page.append("</table>");
        page.append("<h2 align='center'>Cadastrar novo produto</h2>");
        page.append("<form action='/miniloja/admin' method='POST' align='center'>"
            + "<input type='text' name='description' value='' />"
            + "<input type='text' name='price' value='' />" 
            + "<input type='submit' value='Cadastrar' name='botaoCadastrar' />" 
            + "<input type='hidden' name='metodo' value='cadastrar'"    
            + "</form>");
        page.append("</body></html>");
        
        return page.toString();
    }
    
}
