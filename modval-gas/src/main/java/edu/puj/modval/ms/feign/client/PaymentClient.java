package edu.puj.modval.ms.feign.client;

import edu.puj.modval.ms.config.SoapClientConfiguration;
import edu.puj.modval.ms.feign.model.BalanceRequest;
import edu.puj.modval.ms.feign.model.BalanceResponse;
import edu.puj.modval.ms.feign.model.PaymentRequest;
import edu.puj.modval.ms.feign.model.PaymentResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "paymentContract",
    url = "${modval.payment-service-url}",
    configuration = SoapClientConfiguration.class
)
public interface PaymentClient {
    @PostMapping(value = "", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    BalanceResponse getBalance(@RequestBody BalanceRequest balanceRequest);

    @Headers("SOAPAction: pagar")
    @PostMapping(value = "", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    PaymentResponse pay(@RequestBody PaymentRequest paymentRequest);

    @Headers("SOAPAction: compensar")
    @PostMapping(value = "", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    PaymentResponse returnPay(@RequestBody PaymentRequest paymentRequest);
}
