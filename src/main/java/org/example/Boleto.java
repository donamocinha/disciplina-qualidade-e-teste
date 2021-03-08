package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Boleto {

    private UUID codigo;
    private BigDecimal valorPago;
    private LocalDate dataVencimento;

    public Boleto(BigDecimal valorPago, LocalDate dataVencimento) {
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        codigo = UUID.randomUUID();
    }

    public BigDecimal recuperaValorPago() {
        return valorPago;
    }

    public UUID recuperaCodigo() {
        return codigo;
    }

}
