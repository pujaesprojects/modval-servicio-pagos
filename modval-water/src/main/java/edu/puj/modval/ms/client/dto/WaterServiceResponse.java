package edu.puj.modval.ms.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WaterServiceResponse {

    private String mensaje;
    private int idFactura;
    private double valorFactura;
}