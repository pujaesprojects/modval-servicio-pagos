package edu.puj.modval.ms.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WaterServiceResponse {

    protected long idFactura;
    protected double valorFactura;

    public WaterServiceResponse(long valorFactura) {
        this.valorFactura = valorFactura;
    }

}