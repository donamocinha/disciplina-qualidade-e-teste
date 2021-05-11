package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public class ProcessadorBoletos implements IProcessadorBoletos {

    public ProcessadorBoletos() {
    }

    @Override
    public void processaPagamento(ArrayList<Boleto> boletos, Fatura fatura) {
        Optional<BigDecimal> totalValorBoletos =
                boletos.stream().map(Boleto::recuperaValorPago).reduce(BigDecimal::add);

        totalValorBoletos.ifPresent(total-> {
            if(pagamentoSuficiente(fatura.valorFatura(), total))
             fatura.faturaPaga();
            else {
                System.out.println("PAGAMENTO INSUFICIENTE");
            }
        });

        if(totalValorBoletos.isPresent()) {
            ArrayList<Pagamento> pagamentos = new ArrayList<>();
            boletos.forEach(boleto -> {
                Pagamento pagamento = new Pagamento(boleto.recuperaValorPago(), TipoPagamento.BOLETO,
                        boleto.recuperaCodigo());
                pagamentos.add(pagamento);
            });

            fatura.salvaPagamentos(pagamentos);
        }
    };


    @Override
    public boolean pagamentoSuficiente(BigDecimal valorFatura, BigDecimal valorTotalBoletos) {
        return valorFatura.compareTo(valorTotalBoletos) <= 0;
    }

}
