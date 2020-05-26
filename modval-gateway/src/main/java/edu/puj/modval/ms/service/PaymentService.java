package edu.puj.modval.ms.service;

import edu.puj.modval.ms.controller.IPaymentController;
import edu.puj.modval.ms.dto.PaymentDTO;
import edu.puj.modval.ms.dto.PaymentResponseDTO;
import edu.puj.modval.ms.feign.client.IPaymentClient;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Import(FeignClientsConfiguration.class)
public class PaymentService implements IPaymentService {
    private static final String START_WITH_KEY = "startsWith";
    private static final String NO_SERVICE_ERROR = "El servicio no se encuentra disponible, intente más tarde";

    private final Decoder decoder;
    private final Encoder encoder;
    private final Contract contract;
    private final DiscoveryClient discoveryClient;

    public PaymentService(Decoder decoder, Encoder encoder, Contract contract, DiscoveryClient discoveryClient) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.contract = contract;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public PaymentDTO getBalance(String reference) {
        ServiceInstance serviceInstance = this.getServiceInstance(reference);
        if (serviceInstance != null) {
            IPaymentClient client = this.loadClient(serviceInstance.getUri().toString());
            return client.getBalance(reference);
        } else {
            PaymentResponseDTO responseDTO = new PaymentResponseDTO();
            responseDTO.setError(true);
            responseDTO.setMessage(NO_SERVICE_ERROR);
            return responseDTO;
        }
    }

    @Override
    public PaymentDTO pay(PaymentDTO payment) {
        ServiceInstance serviceInstance = this.getServiceInstance(payment.getReferenceCode());
        if (serviceInstance != null) {
            IPaymentClient client = this.loadClient(serviceInstance.getUri().toString());
            return client.pay(payment);
        } else {
            PaymentResponseDTO responseDTO = new PaymentResponseDTO();
            responseDTO.setError(true);
            responseDTO.setMessage(NO_SERVICE_ERROR);
            return responseDTO;
        }
    }

    @Override
    public PaymentDTO returnPay(PaymentDTO payment) {
        ServiceInstance serviceInstance = this.getServiceInstance(payment.getReferenceCode());
        if (serviceInstance != null) {
            IPaymentClient client = this.loadClient(serviceInstance.getUri().toString());
            return client.returnPay(payment);
        } else {
            PaymentResponseDTO responseDTO = new PaymentResponseDTO();
            responseDTO.setError(true);
            responseDTO.setMessage(NO_SERVICE_ERROR);
            return responseDTO;
        }
    }

    private IPaymentClient loadClient(final String url) {
        IPaymentClient paymentClient = Feign.builder()
                .encoder(this.encoder)
                .decoder(this.decoder)
                .logLevel(Logger.Level.FULL)
                .contract(this.contract)
                .target(IPaymentClient.class, url);
        return paymentClient;
    }

    private ServiceInstance getServiceInstance(String referenceCode) {
        List<ServiceInstance> instances = this.discoveryClient.getServices()
                .stream()
                .map(serviceId -> {
                    List<ServiceInstance> serviceInstance = this.discoveryClient.getInstances(serviceId);
                    if (serviceInstance.size() > 0
                            && serviceInstance.get(0).getMetadata().containsKey(START_WITH_KEY)
                            && referenceCode.startsWith(serviceInstance.get(0).getMetadata().get(START_WITH_KEY))) {
                        return serviceInstance.get(0);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return instances.size() > 0 ? instances.get(0) : null;
    }
}
