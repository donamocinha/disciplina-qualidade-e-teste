package calculadora;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.table.TableCellRenderer;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    public void inicializa() {
        calc = new Calculadora();
    }

    @DisplayName("Testa a soma de dois números")
    @Test
    public void testSomaDoisNumeros() {
        int soma = calc.soma(4, 5);
        Assertions.assertEquals(9, soma);
    }

    @Test
    public void testDivisaoDoisNumeros() {
        int divisao = calc.divisao(8, 4);
        assertTrue(divisao == 2);
    }

    @Test
    public void testDivisaoPorZero() {
        try {
            int divisao = calc.divisao(8, 0);
            fail("Exceção não lançada");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void testDivisaoPorZeroComAssertThrows() {
        assertThrows(ArithmeticException.class,
                () -> calc.divisao(8, 0));
    }

    @Test
    void testSubtracaoPositivos() {
        int res = calc.subtracao(17, 4);
        Assertions.assertEquals(13, res);
    }

    @Test
    void testSubtracaoNegativos() {
        int res = calc.subtracao(-17, -4);
        Assertions.assertEquals(-13, res);
    }

    @Test
    void testSubtracaoPositivoNegativo() {
        int res = calc.subtracao(17, -4);
        Assertions.assertEquals(21, res);
    }

    @Test
    void testMultiplicacaoPositivos() {
        int res = calc.multiplicacao(3, 6);
        Assertions.assertEquals(18, res);
    }

    @Test
    void testMultiplicacaoNegativos() {
        int res = calc.multiplicacao(-3, -6);
        Assertions.assertEquals(18, res);
    }

    @Test
    void testMultiplicacaoZero() {
        int res = calc.multiplicacao(3, 0);
        Assertions.assertEquals(0, res);
    }

    @Test
    void testMultiplicacaoOrdem() {
        int res1 = calc.multiplicacao(3, 6);
        int res2 = calc.multiplicacao(6, 3);
        Assertions.assertEquals(res1, res2);
    }


    @Test
    void testSomatoriaPositvo() {
        int res = calc.somatoria(5);
        Assertions.assertEquals(15, res);
    }

    @Test
    void testSomatoriaNegativo() {
        int res = calc.somatoria(-5);
        Assertions.assertEquals(0, res);
    }

    @Test
    void testSomatoriaZero() {
        int res = calc.somatoria(0);
        Assertions.assertEquals(0, res);
    }


    @Test
    void testEhPositivoPositivo() {
        boolean res = calc.ehPositivo(2);
        Assertions.assertTrue(res);
    }

    @Test
    void testEhPositivoNegativo() {
        boolean res = calc.ehPositivo(-2);
        Assertions.assertFalse(res);

    }

    @Test
    void testEhPositivoZero() {
        boolean res = calc.ehPositivo(-0);
        Assertions.assertTrue(res);
    }

    @Test
    void testComparaMenorPositivos() {
        int res = calc.compara(1, 2);
        Assertions.assertEquals(-1, res);
    }

    @Test
    void testComparaMaiorPositivos() {
        int res = calc.compara(2, 1);
        Assertions.assertEquals(1, res);
    }

    @Test
    void testComparaIgualPositivos() {
        int res = calc.compara(1, 1);
        Assertions.assertEquals(0, res);
    }

    @Test
    void testComparaMaiorNegativos() {
        int res = calc.compara(-1, -2);
        Assertions.assertEquals(1, res);
    }

    @Test
    void testComparaMenorNegativos() {
        int res = calc.compara(-2, -1);
        Assertions.assertEquals(-1, res);
    }

    @Test
    void testComparaIgualNegativos() {
        int res = calc.compara(-2, -2);
        Assertions.assertEquals(0, res);
    }


}
