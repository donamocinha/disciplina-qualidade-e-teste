package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface IProcessadorBoletos {

     void processaPagamento(ArrayList<Boleto> boletos, Fatura fatura);

     boolean pagamentoSuficiente(BigDecimal valorFatura, BigDecimal valorTotalBoletos);

}
