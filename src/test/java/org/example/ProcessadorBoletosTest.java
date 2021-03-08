package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProcessadorBoletosTest {

    Fatura fatura;
    LocalDate dataVencimento = LocalDate.now().plusDays(7);
    BigDecimal valorTotal = new BigDecimal(1000);
    ProcessadorBoletos processadorBoletos = new ProcessadorBoletos();

    @BeforeEach
    public void inicializa() {
        fatura = new Fatura("Silvia", dataVencimento, valorTotal);
    }

    @Test
    void testEstadoInicialFatura() {
        assertEquals(null, fatura.situacao());
    }

    @Test
    void testProcessaListaVazia() {
        ArrayList<Boleto> boletos = new ArrayList<>();

        processadorBoletos.processaPagamento(boletos, fatura);

        assertEquals(0, fatura.recuperaPagamentos().size());
        assertNull(fatura.situacao());

    }

    @Test
    void testFaturaPaga() {
        Boleto boleto = new Boleto(fatura.valorFatura(), dataVencimento);
        ArrayList<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto);
        assertNotNull(boleto.recuperaCodigo());
        processadorBoletos.processaPagamento(boletos, fatura);

        Assertions.assertAll(()-> {
            assertEquals(EstadoPagamento.PAGA, fatura.situacao());
            assertNotNull(fatura.recuperaPagamentos());
            assertFalse(fatura.recuperaPagamentos().isEmpty());
        });
        if(fatura.recuperaPagamentos() != null && !fatura.recuperaPagamentos().isEmpty()) {
            assertEquals(1, fatura.recuperaPagamentos().size());
            Pagamento pagamento = fatura.recuperaPagamentos().get(0);
            assertEquals(boleto.recuperaValorPago(), pagamento.recuperarValorPago());
            assertEquals(TipoPagamento.BOLETO, pagamento.recuperarTipoPagamento());
            assertEquals(boleto.recuperaCodigo(), pagamento.recuperarCodigoBoleto());
        }

    }

    @Test
    void testBoletosInsuficientes() {
        Boleto boleto1 = new Boleto(BigDecimal.TEN.multiply(BigDecimal.TEN), dataVencimento);
        Boleto boleto2 = new Boleto(BigDecimal.TEN.multiply(BigDecimal.valueOf(20)),
                dataVencimento);

        ArrayList<Boleto> boletos = new ArrayList<>();

        assertNotNull(boleto1.recuperaCodigo());
        assertNotNull(boleto2.recuperaCodigo());

        boletos.add(boleto1);
        boletos.add(boleto2);

        processadorBoletos.processaPagamento(boletos, fatura);

        assertNull(fatura.situacao());
        assertNotNull(fatura.recuperaPagamentos());
        assertFalse(fatura.recuperaPagamentos().isEmpty());

        assertEquals(2, fatura.recuperaPagamentos().size());
        Pagamento pagamento1 = fatura.recuperaPagamentos().get(0);
        Pagamento pagamento2 = fatura.recuperaPagamentos().get(1);
        assertEquals(boleto1.recuperaValorPago(), pagamento1.recuperarValorPago());
        assertEquals(TipoPagamento.BOLETO, pagamento1.recuperarTipoPagamento());
        assertEquals(boleto1.recuperaCodigo(), pagamento1.recuperarCodigoBoleto());

        assertEquals(boleto2.recuperaValorPago(), pagamento2.recuperarValorPago());
        assertEquals(TipoPagamento.BOLETO, pagamento2.recuperarTipoPagamento());
        assertEquals(boleto2.recuperaCodigo(), pagamento2.recuperarCodigoBoleto());
    }

    @Test
    void testFaturaPagaComSobra() {
        Boleto boleto1 = new Boleto(BigDecimal.TEN.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN), dataVencimento);
        Boleto boleto2 = new Boleto(BigDecimal.TEN.multiply(BigDecimal.valueOf(20)),
                dataVencimento);

        ArrayList<Boleto> boletos = new ArrayList<>();

        assertNotNull(boleto1.recuperaCodigo());
        assertNotNull(boleto2.recuperaCodigo());

        boletos.add(boleto1);
        boletos.add(boleto2);

        processadorBoletos.processaPagamento(boletos, fatura);

        assertEquals(EstadoPagamento.PAGA, fatura.situacao());
        assertNotNull(fatura.recuperaPagamentos());
        assertFalse(fatura.recuperaPagamentos().isEmpty());

        assertEquals(2, fatura.recuperaPagamentos().size());
        Pagamento pagamento1 = fatura.recuperaPagamentos().get(0);
        Pagamento pagamento2 = fatura.recuperaPagamentos().get(1);
        assertEquals(boleto1.recuperaValorPago(), pagamento1.recuperarValorPago());
        assertEquals(TipoPagamento.BOLETO, pagamento1.recuperarTipoPagamento());
        assertEquals(boleto1.recuperaCodigo(), pagamento1.recuperarCodigoBoleto());

        assertEquals(boleto2.recuperaValorPago(), pagamento2.recuperarValorPago());
        assertEquals(TipoPagamento.BOLETO, pagamento2.recuperarTipoPagamento());
        assertEquals(boleto2.recuperaCodigo(), pagamento2.recuperarCodigoBoleto());
    }


}