package edu.puj.modval.ms.controller;

import edu.puj.modval.ms.dto.PaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.puj.modval.ms.service.WaterService;

import java.time.LocalDate;


@RestController
public class WaterPaymentController implements IPaymentController {

    private final WaterService waterService;

    public WaterPaymentController(WaterService waterService) {
        this.waterService = waterService;
    }

    @Override
    public ResponseEntity<PaymentDTO> getBalance(String referenceCode) {
        var paymentDTO = waterService.getBalance(referenceCode);
        return ResponseEntity.ok(paymentDTO);
    }

    @Override
    public ResponseEntity<PaymentDTO> pay(PaymentDTO payment) {
        var paymentDTO = waterService.pay(payment);
        return ResponseEntity.ok(paymentDTO);
    }

    @Override
    public ResponseEntity<PaymentDTO> returnPay(PaymentDTO payment) {
        return null;
    }
}
