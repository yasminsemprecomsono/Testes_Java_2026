package org.example.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoEntityTest {

    @Test
    void deveCalcularValorEstoque(){
        ProdutoEntity produtoEntity = new ProdutoEntity("camiseta",25.0,4);
        double total = produtoEntity.calcularValorEstoque();
        assertEquals(100.0,total);
    }

    @Test
    void deveCalcularPrecoComDesconto(){
        ProdutoEntity produtoEntity = new ProdutoEntity("camiseta",80.0,1);
        double produtoComDesconto = produtoEntity.calcularPrecoComDesconto(25.0);
        assertEquals(60.0,produtoComDesconto);
    }

    @Test
    void deveRejeitarDadosInvalidos(){
        assertThrows(IllegalArgumentException.class,()->{
            new ProdutoEntity(" ",10.0,5);
        });
        assertThrows(IllegalArgumentException.class,()->{
            new ProdutoEntity("Camisa",0.0,5);
        });
        assertThrows(IllegalArgumentException.class,()->{
            new ProdutoEntity("Camisa",10.0,-1);
        });
    }

    @Test
    void deveRejeitarDescontoInvalido(){
        ProdutoEntity  produtoEntity = new ProdutoEntity("Camisa",100.0,2);
        assertThrows(IllegalArgumentException.class,()->{
            produtoEntity.calcularPrecoComDesconto(-10);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            produtoEntity.calcularPrecoComDesconto(150);
        });
    }

    @Test
    void deveVerificarTudo(){
        ProdutoEntity produtoEntity = new ProdutoEntity("Camisa",100.0,2);
        assertAll(
                ()->assertEquals("Camisa",produtoEntity.getNome()),
                ()->assertEquals(100.0,produtoEntity.getPreco()),
                ()->assertEquals(2,produtoEntity.getQuantidade()),
                ()->assertEquals(200.0,produtoEntity.calcularValorEstoque())
        );
    }
}

