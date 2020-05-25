package edu.puj.modval.ms.service;

import edu.puj.modval.ms.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Clase base para los servicios de pago.
 */
@Service
@Slf4j
public class PaymentService implements IPaymentService {
    @Override
    public PaymentDTO getBalance() {
        log.debug("Obtener el saldo del gas");
        return new PaymentDTO();
    }

    @Override
    public PaymentDTO pay(PaymentDTO payment) {
        log.debug("Pagando el gas");
        return null;
    }

    @Override
    public PaymentDTO returnPay(PaymentDTO payment) {
        log.debug("Compensar valor pagado del gas");
        return null;
    }
}
