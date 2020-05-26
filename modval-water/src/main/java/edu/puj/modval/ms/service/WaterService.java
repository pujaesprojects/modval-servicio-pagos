package edu.puj.modval.ms.service;

import org.springframework.stereotype.Service;

import edu.puj.modval.ms.client.ClientWaterProvider;
import edu.puj.modval.ms.client.model.*;

@Service
public class WaterService {

  private final ClientWaterProvider clientWaterProvider;

  public WaterService(ClientWaterProvider clientWaterProvider) {
    this.clientWaterProvider = clientWaterProvider;
  }

  public WaterServiceResponse getBalance(String reference) {
    return clientWaterProvider.getInfoFactura(reference);
  }

}