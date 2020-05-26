package edu.puj.modval.ms.client.dto;

import lombok.Data;

@Data
public class WaterServiceClient {

  private Integer idFactura;
  private Double valorFactura;
  private String mensajeOK;
  private String mensajeBad;
  
}