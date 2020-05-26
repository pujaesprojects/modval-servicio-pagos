package edu.puj.modval.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.puj.modval.ms.client.dto.WaterServiceResponse;
import edu.puj.modval.ms.client.dto.WaterServiceRequest;

@Component
@FeignClient(name="waterProvider", url = "${modval.payment-service-url}")
public interface ClientWaterProvider {
  
  @RequestMapping(method = RequestMethod.GET, value= "/payments/{idFactura}")
    WaterServiceResponse getInfoFactura(@PathVariable("idFactura") String idFactura);

    @RequestMapping(method = RequestMethod.POST, value="/payments/{idFactura}", consumes = "application/json")
    WaterServiceResponse pagarFactura(@PathVariable("idFactura") String idFactura, @RequestBody WaterServiceRequest factura );
}

