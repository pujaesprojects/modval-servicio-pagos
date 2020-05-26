package edu.puj.modval.ms.controller;

import edu.puj.modval.ms.dto.PaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPaymentController {

    @GetMapping(value = "/get-balance/{referenceCode}")
    PaymentDTO getBalance(@PathVariable("referenceCode") String referenceCode);

    @PostMapping(value = "/pay")
    public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentDTO payment);
}
