package edu.puj.modval.ms.controller;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController implements IPaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<PaymentDTO> getBalance(@PathVariable("referenceCode") String referenceCode) {
        return ResponseEntity.ok(paymentService.getBalance(referenceCode));
    }

    @Override
    public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentDTO payment) {
        return ResponseEntity.ok(paymentService.pay(payment));
    }

    @Override
    public ResponseEntity<PaymentDTO> returnPay(@RequestBody PaymentDTO payment) {
        return ResponseEntity.ok(paymentService.returnPay(payment));
    }
}
