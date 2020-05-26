package edu.puj.modval.ms.feign.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
    name = "PagoResource",
    namespace = "http://www.servicios.co/pagos/schemas"
)
public class PaymentRequest {
    private BalanceRequest referenciaFactura;
    private Double totalPagar;

    public PaymentRequest() {}

    public PaymentRequest(final String referenciaFactura, final Double totalPagar) {
        this.setReferenciaFactura(new BalanceRequest(referenciaFactura));
        this.setTotalPagar(totalPagar);
    }

    @XmlElement(name = "referenciaFactura", namespace = "http://www.servicios.co/pagos/schemas")
    public BalanceRequest getReferenciaFactura() {
        return referenciaFactura;
    }

    public void setReferenciaFactura(BalanceRequest referenciaFactura) {
        this.referenciaFactura = referenciaFactura;
    }

    @XmlElement(namespace = "http://www.servicios.co/pagos/schemas")
    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("referenciaFactura", referenciaFactura)
            .append("totalPagar", totalPagar)
            .toString();
    }
}
