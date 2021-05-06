package carrinho;

import calculadora.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produto.Produto;
import produto.ProdutoNaoEncontradoException;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {
    private Carrinho carrinho;

    @BeforeEach
    public void inicializa() {
        carrinho = new Carrinho();

    }

    @Test
    void testGetValorTotal() throws ProdutoNaoEncontradoException {
        Assertions.assertEquals(0.00, carrinho.getValorTotal());

        Produto produto1 = new Produto("Maçã", 3.00);
        carrinho.addItem(produto1);
        Assertions.assertEquals(3.00, carrinho.getValorTotal());

        Produto produto2 = new Produto("Banana", 2.00);
        carrinho.addItem(produto2);
        Assertions.assertEquals(5.00, carrinho.getValorTotal());

        carrinho.removeItem(produto1);
        Assertions.assertEquals(2.00, carrinho.getValorTotal());
    }

    @Test
    void testGetValorTotalItemNulo() {
        Assertions.assertEquals(0.00, carrinho.getValorTotal());

        carrinho.addItem(null);

        assertThrows(NullPointerException.class, () -> {
            carrinho.getValorTotal();
        });
    }

    @Test
    void testAddItem() {
        Assertions.assertEquals(0, carrinho.getQtdeItems());

        Produto produto1 = new Produto("Maçã", 3.00);
        carrinho.addItem(produto1);
        carrinho.addItem(produto1);
        Assertions.assertEquals(2, carrinho.getQtdeItems());

        Produto produto2 = new Produto("Banana", 2.00);
        carrinho.addItem(produto2);
        Assertions.assertEquals(3, carrinho.getQtdeItems());
    }

    @Test
    void testRemoveItem() {
        Assertions.assertEquals(0, carrinho.getQtdeItems());

        Produto produto1 = new Produto("Maçã", 3.00);
        carrinho.addItem(produto1);
        carrinho.addItem(produto1);

        Produto produto2 = new Produto("Banana", 2.00);
        carrinho.addItem(produto2);

        Assertions.assertEquals(3, carrinho.getQtdeItems());

        Assertions.assertDoesNotThrow(() -> {
            carrinho.removeItem(produto1);
        });
        Assertions.assertEquals(2, carrinho.getQtdeItems());

        Assertions.assertDoesNotThrow(() -> {
            carrinho.removeItem(produto1);

        });
        Assertions.assertEquals(1, carrinho.getQtdeItems());
    }

    @Test
    void testRemoveItemNaoExistente() {
        Assertions.assertEquals(0, carrinho.getQtdeItems());

        Produto produto1 = new Produto("Maçã", 3.00);

        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            carrinho.removeItem(produto1);
        });
    }

    @Test
    void testGetQtdItens() throws ProdutoNaoEncontradoException {
        Assertions.assertEquals(0, carrinho.getQtdeItems());

        Produto produto1 = new Produto("Maçã", 3.00);
        carrinho.addItem(produto1);
        carrinho.addItem(produto1);
        Assertions.assertEquals(2, carrinho.getQtdeItems());

        Produto produto2 = new Produto("Banana", 2.00);
        carrinho.addItem(produto2);
        Assertions.assertEquals(3, carrinho.getQtdeItems());

        carrinho.removeItem(produto1);
        Assertions.assertEquals(2, carrinho.getQtdeItems());
    }

    @Test
    void testGetQtdItensItemNulo() {
        Assertions.assertEquals(0, carrinho.getQtdeItems());

        carrinho.addItem(null);
        Assertions.assertEquals(1, carrinho.getQtdeItems());


    }

    @Test
    void testEsvazia() {
        Assertions.assertEquals(0.00, carrinho.getValorTotal());

        Produto produto1 = new Produto("Maçã", 3.00);
        carrinho.addItem(produto1);
        carrinho.addItem(produto1);

        Produto produto2 = new Produto("Banana", 2.00);
        carrinho.addItem(produto2);

        carrinho.esvazia();

        Assertions.assertEquals(0, carrinho.getQtdeItems());
        Assertions.assertEquals(0, carrinho.getValorTotal());

    }
}