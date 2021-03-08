package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Fatura {

    private String nome;
    private LocalDate dataVencimento;
    private BigDecimal valorTotal;

    private ArrayList<Pagamento> pagamentos;
    private EstadoPagamento status;

    public Fatura(String nomeCliente, LocalDate vencimento, BigDecimal total) {
        nome = nomeCliente;
        dataVencimento = vencimento;
        valorTotal = total;
    }

    public void faturaPaga() {
        status = EstadoPagamento.PAGA;
    }

    public EstadoPagamento situacao() {
        return status;
    }

    public void salvaPagamentos(ArrayList<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public BigDecimal valorFatura() {
        return valorTotal;
    }

    public ArrayList<Pagamento> recuperaPagamentos() {
        return pagamentos;
    }

}
