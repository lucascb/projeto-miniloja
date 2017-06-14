/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 *
 * @author lucas
 */
public class DaoLoja {
    private final Path file = Paths.get("/home/lucas/Documents/ufu/miniloja/loja.txt");
    private Loja shop;
    private int count;

    public DaoLoja() throws IOException {
        this.shop = parseFile(Files.lines(file));
        this.count = shop.getProdutos().size();
    }

    public Loja getLoja() {
        return shop;
    }

    public void setLoja(Loja shop) {
        this.shop = shop;
    }
    
    public void addProduto(String desc, double preco) {
        count++;
        this.shop.addProduto(new Produto(count, desc, preco));
        save();
    }
    
    public void removeProduto(int id) {
        for (Produto prod : this.shop.getProdutos()) {
            if (prod.getId() == id) {
                this.shop.removeProduto(prod);
                save();
                for (Produto p : shop.getProdutos())
                    System.out.println(p);
                return;
            }
        }
    }
    
    // Parseia uma string e devolve uma loja
    private Loja parseFile(Stream<String> file) {
        Loja loja = new Loja();
        
        file.forEach(line -> {
            String[] values = line.split(" ");
            loja.addProduto(new Produto(Integer.parseInt(values[0]),
                                        values[1],
                                        Double.parseDouble(values[2])));
        });
        
        return loja;
    }
    
    // Persiste a loja no arquivo
    private void save() {
        try (BufferedWriter bw = Files.newBufferedWriter(
                file, 
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)
            ) {
            this.shop.getProdutos().forEach(prod -> {
                try {
                    bw.write(prod.getId() + " " 
                            + prod.getDescription() + " " 
                            + prod.getPrice() + "\n");    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
