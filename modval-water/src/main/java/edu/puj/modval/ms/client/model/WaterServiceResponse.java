package edu.puj.modval.ms.client.model;

public class WaterServiceResponse {

  protected int idFactura;
  protected int valorFactura;
  protected String mensaje;

  public WaterServiceResponse(int valorFactura) {
    this.valorFactura = valorFactura;
  }

  public int getValorFactura() {
    return valorFactura;
  }

  public void setValorFactura(int valorFactura) {
    this.valorFactura = valorFactura;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public int getIdFactura() {
    return idFactura;
  }

  public void setIdFactura(int idFactura) {
    this.idFactura = idFactura;
  } 
  
}