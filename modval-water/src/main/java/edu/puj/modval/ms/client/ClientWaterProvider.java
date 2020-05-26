package edu.puj.modval.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import edu.puj.modval.ms.client.dto.WaterServiceResponse;

@FeignClient(name="waterProvider", url="http://localhost:9090/servicios/pagos/v1")
public interface ClientWaterProvider {
  
  @RequestMapping(method = RequestMethod.GET, value= "/payments/{idFactura}")
    WaterServiceResponse getInfoFactura(@PathVariable("idFactura") String idFactura);
}

