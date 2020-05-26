package edu.puj.modval.ms.service;

import edu.puj.modval.ms.client.ClientWaterProvider;
import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.dto.PaymentResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import edu.puj.modval.ms.client.dto.WaterServiceRequest;
import edu.puj.modval.ms.client.dto.WaterServiceResponse;

@Service
public class WaterService implements IPaymentService {

    private final ClientWaterProvider clientWaterProvider;

    @Value("${eureka.instance.metadata-map.convenio}")
    private String convenioName;

    public WaterService(ClientWaterProvider clientWaterProvider) {
        this.clientWaterProvider = clientWaterProvider;
    }

    @Override
    public PaymentDTO getBalance(String reference) {
        var resultBalance = clientWaterProvider.getInfoFactura(reference    );
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setValue(resultBalance.getValorFactura());
        paymentDTO.setDate(LocalDate.now());
        paymentDTO.setReferenceCode(reference);
        paymentDTO.setService(convenioName);

        return paymentDTO;
    }

    @Override
    public PaymentDTO pay(PaymentDTO payment) {   
      WaterServiceRequest waterServiceRequest = new WaterServiceRequest();
      var resultPay = clientWaterProvider.pagarFactura(payment.getReferenceCode(), waterServiceRequest);
      PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
      paymentResponseDTO.setValue(payment.getValue());
      paymentResponseDTO.setDate(LocalDate.now());
      paymentResponseDTO.setReferenceCode(payment.getReferenceCode());
      paymentResponseDTO.setService(convenioName);
      paymentResponseDTO.setMessage(resultPay.getMensaje());
      return paymentResponseDTO;
    }

    @Override
    public PaymentDTO returnPay(PaymentDTO payment) {
        return null;
    }

}