<%-- 
    Document   : Carrinho
    Created on : Jun 13, 2017, 6:33:32 PM
    Author     : lucas
--%>

<%@page import="models.ItemCarrinho"%>
<%@page import="models.DaoLoja"%>
<%@page import="models.Loja"%>
<%@page import="models.Produto"%>
<%@page import="models.Carrinho"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Seja bem-vindo!</h1>
        <a href="loja.jsp">Gerenciar loja</a>
        <%-- Carrega as informacoes da loja e do carrinho --%>
        <%  
            Loja loja = new DaoLoja().getLoja();
            Carrinho carr = (Carrinho) request.getSession().getAttribute("carrinho");
            if (carr == null) {
                carr = new Carrinho();
                request.getSession().setAttribute("carrinho", carr);
            }
        %>
        <%-- Tabela de produtos da loja --%>
        <table>
            <tr>
                <th>Produto</th>
                <th>Preço</th>
                <th>Comprar</th>
            </tr>
            <% for (Produto prod : loja.getProdutos()) { %>
            <tr>
                <td align="center"><%= prod.getDescription() %></td>
                <td align="center"><%= prod.getPrice() %></td>
                <td align="center">
                    <form action="/miniloja/comprar" method="POST">
                        <input type="submit" value="+" name="botaoComprar" />
                        <input type="hidden" value="<%= prod.getId() %>" name="idComprarProd" />
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <%-- Tabela de produtos do carrinho --%>
        <h1>Meu carrinho</h1>
        <table>
            <tr>
                <th>Produto</th>
                <th>Preço</th>
                <th>Remover</th>
            </tr>
            <% for (ItemCarrinho item : carr.getItens()) { %>
                <tr>
                    <td align="center"><%= item.getProduto().getDescription() %></td>
                    <td align="center"><%= item.getProduto().getPrice() %></td>
                    <td align="center">
                        <form action="/miniloja/comprar" method="POST">
                            <input type="submit" value="x" name="botaoRemover" />
                            <input type="hidden" value="<%= item.getId() %>" name="idRemoverProd" />
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
        <p><b>Subtotal:</b> R$ <%= carr.getPrecoTotal() %></p>
        <a href="index.html">Voltar para a Página Inicial</a>
    </body>
</html>
