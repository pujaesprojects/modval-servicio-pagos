package edu.puj.modval.ms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {
    private Double value;
    private LocalDate date;
    private String service;
    private String referenceCode;
}
