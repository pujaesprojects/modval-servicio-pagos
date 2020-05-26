package edu.puj.modval.ms.controller.vm;

import edu.puj.modval.ms.controller.IPaymentController;
import edu.puj.modval.ms.dto.PaymentDTO;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "payment")
@Headers("Content-Type: application/json")
public interface PaymentClientController extends IPaymentController {

    //@RequestLine("GET /get-balance")
    //ResponseEntity<PaymentDTO> getBalance();
}
