package edu.puj.modval.ms.service;

import edu.puj.modval.ms.dto.PaymentDTO;

public interface IPaymentService {
    PaymentDTO getBalance(String reference);

    PaymentDTO pay(PaymentDTO payment);

    PaymentDTO returnPay(PaymentDTO payment);
}
