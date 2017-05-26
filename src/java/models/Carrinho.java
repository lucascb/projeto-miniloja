/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Carrinho {
    private String cliente;
    private List<Produto> produtos = new ArrayList<>();

    public Carrinho() {}
    
    public Carrinho(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void addProduto(Produto p) {
        this.produtos.add(p);
    }
    
    public boolean removeProduto(int id) {
        return produtos.removeIf(prod -> prod.getId() == id);
    }
    
    public Produto getProduto(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }
    
    public List<Produto> getProdutos() {
        return this.produtos;
    }
}
