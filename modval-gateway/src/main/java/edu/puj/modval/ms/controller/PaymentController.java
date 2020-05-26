package edu.puj.modval.ms.controller;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping(value = "/get-balance/{referenceCode}")
    public ResponseEntity<PaymentDTO> getBalance(@PathVariable String referenceCode) {
        return ResponseEntity.ok(this.paymentService.getBalance(referenceCode));
    }

    @Override
    @PostMapping(value = "/pay")
    public ResponseEntity<PaymentDTO> pay(PaymentDTO payment) {
        return ResponseEntity.ok(this.paymentService.pay(payment));
    }

    @Override
    @PostMapping(value = "/return-pay")
    public ResponseEntity<PaymentDTO> returnPay(PaymentDTO payment) {
        return ResponseEntity.ok(this.paymentService.returnPay(payment));
    }
}
