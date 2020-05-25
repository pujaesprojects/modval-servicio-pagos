package edu.puj.modval.ms.controller;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/get-balance")
    public ResponseEntity<PaymentDTO> getBalance() {
        return ResponseEntity.ok(paymentService.getBalance());
    }
}
