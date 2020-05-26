package edu.puj.modval.ms.controller;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController implements IPaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public PaymentDTO getBalance(String referenceCode) {
        //return ResponseEntity.ok(paymentService.getBalance(referenceCode));
        return paymentService.getBalance(referenceCode);
    }

    @Override
    public ResponseEntity<PaymentDTO> pay(PaymentDTO payment) {
        return ResponseEntity.ok(paymentService.pay(payment));
    }
}
