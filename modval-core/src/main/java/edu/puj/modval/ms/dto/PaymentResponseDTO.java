package edu.puj.modval.ms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO extends PaymentDTO {
    private String message;
    private Boolean error = false;
}
