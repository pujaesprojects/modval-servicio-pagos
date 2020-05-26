package edu.puj.modval.ms.service;

import edu.puj.modval.ms.client.dto.WaterServiceResponse;
import edu.puj.modval.ms.dto.PaymentDTO;
import org.springframework.stereotype.Service;

import edu.puj.modval.ms.client.ClientWaterProvider;

import java.time.LocalDate;

@Service
public class WaterService implements IPaymentService {

    private final ClientWaterProvider clientWaterProvider;

    public WaterService(ClientWaterProvider clientWaterProvider) {
        this.clientWaterProvider = clientWaterProvider;
    }

    @Override
    public PaymentDTO getBalance(String reference) {
        var resultBalance = clientWaterProvider.getInfoFactura(reference);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setValue(resultBalance.getValorFactura());
        paymentDTO.setDate(LocalDate.now());
        paymentDTO.setReferenceCode(reference);

        return paymentDTO;
    }

    @Override
    public PaymentDTO pay(PaymentDTO payment) {
        return null;
    }

    @Override
    public PaymentDTO returnPay(PaymentDTO payment) {
        return null;
    }

}