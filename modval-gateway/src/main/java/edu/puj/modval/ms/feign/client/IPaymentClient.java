package edu.puj.modval.ms.feign.client;

import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.dto.PaymentResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/payment")
public interface IPaymentClient {
    @GetMapping(
        value = "/get-balance/{referenceCode}",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    PaymentDTO getBalance(@PathVariable("referenceCode") String referenceCode);

    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    PaymentResponseDTO pay(@RequestBody PaymentDTO payment);

    @PostMapping(value = "/return-pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    PaymentResponseDTO returnPay(@RequestBody PaymentDTO payment);
}
