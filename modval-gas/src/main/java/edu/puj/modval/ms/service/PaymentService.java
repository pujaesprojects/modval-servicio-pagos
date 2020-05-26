package edu.puj.modval.ms.service;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.dto.PaymentResponseDTO;
import edu.puj.modval.ms.feign.client.PaymentClient;
import edu.puj.modval.ms.feign.model.BalanceRequest;
import edu.puj.modval.ms.feign.model.BalanceResponse;
import edu.puj.modval.ms.feign.model.PaymentRequest;
import edu.puj.modval.ms.feign.model.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Clase base para los servicios de pago.
 */
@Service
@Slf4j
public class PaymentService implements IPaymentService {
    private final PaymentClient paymentClient;

    public PaymentService(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public PaymentDTO getBalance(String reference) {
        BalanceRequest balanceRequest = new BalanceRequest(reference);
        log.debug("Obtener el saldo del gas, referencia: {}", balanceRequest);

        BalanceResponse response = this.paymentClient.getBalance(balanceRequest);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setDate(LocalDate.now());
        paymentDTO.setReferenceCode(reference);
        paymentDTO.setService("Gas");
        log.debug("Respuesta del servicio: {}", response);
        paymentDTO.setValue(response.getTotalPagar());
        return paymentDTO;
    }

    @Override
    public PaymentDTO pay(PaymentDTO payment) {
        PaymentRequest paymentRequest = new PaymentRequest(payment.getReferenceCode(), payment.getValue());
        log.debug("Pagando el gas, request: {}", paymentRequest);

        PaymentResponse response = this.paymentClient.pay(paymentRequest);
        PaymentResponseDTO paymentDTO = new PaymentResponseDTO();
        paymentDTO.setDate(LocalDate.now());
        paymentDTO.setReferenceCode(response.getReferenciaFactura().getReferenciaFactura());
        paymentDTO.setService("Gas");
        paymentDTO.setMessage(response.getMensaje());
        log.debug("Respuesta del servicio: {}", response);
        paymentDTO.setValue(paymentRequest.getTotalPagar());
        return paymentDTO;
    }

    @Override
    public PaymentDTO returnPay(PaymentDTO payment) {
        log.debug("Compensar valor pagado del gas");
        return null;
    }
}
