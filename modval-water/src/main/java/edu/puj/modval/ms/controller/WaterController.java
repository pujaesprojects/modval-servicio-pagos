package  edu.puj.modval.ms.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.puj.modval.ms.service.WaterService;


@RestController
public class WaterController {

private final WaterService waterService;

    public WaterController(WaterService waterService) {
        this.waterService = waterService;
    }

    @RequestMapping(path="/water/payment/{idFactura}")
    public String getPayment(@PathVariable("idFactura") String idFactura)  {
        return "VALOR FACTURA: " + waterService.getBalance(idFactura);
    }
}
