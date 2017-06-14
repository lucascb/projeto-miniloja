<%-- 
    Document   : loja
    Created on : Jun 14, 2017, 12:27:43 PM
    Author     : lucas
--%>

<%@page import="models.Produto"%>
<%@page import="models.DaoLoja"%>
<%@page import="models.Loja"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciamento Loja</title>
    </head>
    <body>
        <h1>Produtos Cadastrados</h1>
        <%-- Carrega os produtos da loja --%>
        <% Loja loja = new DaoLoja().getLoja(); %>
        <%-- Tabela de produtos cadastrados --%>
        <table>
            <tr>
                <th>Produto</th>
                <th>Preço</th>
                <th></th>
            </tr>
            <% for (Produto prod : loja.getProdutos()) { %>
            <tr>
                <td align="center"><%= prod.getDescription() %></td>
                <td align="center"><%= prod.getPrice() %></td>
                <td align="center">
                    <form action="/miniloja/admin" method="POST">
                        <input type="submit" value="-" name="botaoRemover" />
                        <input type="hidden" value="<%= prod.getId() %>" name="idRemoverProd" />
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <%-- Formulario para adicionar um produto --%>
        <h2>Adicionar um novo produto</h2>
        <form action="/miniloja/admin" method="POST">
            <p>Descrição</p>
            <input type="text" name="description" value="" /></br>
            <p>Preço</p>
            <input type="text" name="price" value='' /></br>
            <p/>
            <input type="submit" value="Cadastrar" name="botaoCadastrar" />
        </form>
        <p/>
        <form action="/miniloja/carrinho.jsp" method="GET">
            <input type="submit" value="Ir para Carrinho" />
        </form>
    </body>
</html>
