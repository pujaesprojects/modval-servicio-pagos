package edu.puj.modval.ms.service;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.dto.PaymentResponseDTO;
import edu.puj.modval.ms.feign.client.PaymentClient;
import edu.puj.modval.ms.feign.model.BalanceRequest;
import edu.puj.modval.ms.feign.model.BalanceResponse;
import edu.puj.modval.ms.feign.model.PaymentRequest;
import edu.puj.modval.ms.feign.model.PaymentResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Clase base para los servicios de pago.
 */
@Service
@Slf4j
public class PaymentService implements IPaymentService {
    private static final String INVALID_BILL_ERROR = "Referencia de factura invalida";
    private static final String SERVICE_ERROR = "Error contactando al convenio, intente más tarde";

    private final PaymentClient paymentClient;

    public PaymentService(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public PaymentDTO getBalance(String reference) {
        BalanceRequest balanceRequest = new BalanceRequest(reference);
        log.debug("Obtener el saldo del gas, referencia: {}", balanceRequest);
        PaymentResponseDTO paymentDTO = new PaymentResponseDTO();
        try {
            BalanceResponse response = this.paymentClient.getBalance(balanceRequest);
            paymentDTO.setDate(LocalDate.now());
            paymentDTO.setReferenceCode(reference);
            paymentDTO.setService("Gas");
            log.debug("Respuesta del servicio: {}", response);
            paymentDTO.setValue(response.getTotalPagar());
        } catch (FeignException.FeignServerException e) {
            paymentDTO.setError(true);
            if(e.contentUTF8().contains(INVALID_BILL_ERROR)) {
                paymentDTO.setMessage(INVALID_BILL_ERROR);
            } else {
                paymentDTO.setMessage(SERVICE_ERROR);
            }
        }
        return paymentDTO;
    }

    @Override
    public PaymentDTO pay(PaymentDTO payment) {
        PaymentRequest paymentRequest = new PaymentRequest(payment.getReferenceCode(), payment.getValue());
        log.debug("Pagando el gas, request: {}", paymentRequest);
        try {
            PaymentResponse response = this.paymentClient.pay(paymentRequest);
            return createResponse(paymentRequest, response);
        } catch (FeignException.FeignServerException e) {
            PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
            paymentResponseDTO.setMessage(SERVICE_ERROR);
            paymentResponseDTO.setError(true);
            return paymentResponseDTO;
        }
    }

    @Override
    public PaymentDTO returnPay(PaymentDTO payment) {
        PaymentRequest paymentRequest = new PaymentRequest(payment.getReferenceCode(), payment.getValue());
        log.debug("Compensar valor pagado del gas: {}", paymentRequest);
        try {
            PaymentResponse response = this.paymentClient.returnPay(paymentRequest);
            return createResponse(paymentRequest, response);
        } catch (FeignException.FeignServerException e) {
            PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
            paymentResponseDTO.setMessage(SERVICE_ERROR);
            paymentResponseDTO.setError(true);
            return paymentResponseDTO;
        }
    }

    private PaymentDTO createResponse(PaymentRequest paymentRequest, PaymentResponse response) {
        PaymentResponseDTO paymentDTO = new PaymentResponseDTO();
        paymentDTO.setDate(LocalDate.now());
        paymentDTO.setReferenceCode(response.getReferenciaFactura().getReferenciaFactura());
        paymentDTO.setService("Gas");
        paymentDTO.setMessage(response.getMensaje());
        log.debug("Respuesta del servicio: {}", response);
        paymentDTO.setValue(paymentRequest.getTotalPagar());
        return paymentDTO;
    }
}
