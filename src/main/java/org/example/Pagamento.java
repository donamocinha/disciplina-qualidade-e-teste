package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Pagamento {
     private BigDecimal valor;
     private LocalDate data;
     private TipoPagamento tipoPagamento;
     private UUID codigoBoleto;

     public Pagamento(BigDecimal valor, TipoPagamento tipoPagamento, UUID codigoBoleto) {
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.codigoBoleto = codigoBoleto;
        data = LocalDate.now();
     }

     public BigDecimal recuperarValorPago() {
         return valor;
     }

     public TipoPagamento recuperarTipoPagamento() {
         return tipoPagamento;
     }

     public UUID recuperarCodigoBoleto() {
         return codigoBoleto;
     }
}
