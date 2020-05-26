package edu.puj.modval.ms.feign.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/*
@XmlRootElement(
    name = "ReferenciaFactura",
    namespace = "http://www.servicios.co/pagos/schemas"
)
*/
public class BalanceRequest {
    private String referenciaFactura;

    public BalanceRequest() {
    }

    public BalanceRequest(final String referenciaFactura) {
        this.referenciaFactura = referenciaFactura;
    }

    @XmlElement(namespace = "http://www.servicios.co/pagos/schemas")
    public String getReferenciaFactura() {
        return referenciaFactura;
    }

    public void setReferenciaFactura(String referenciaFactura) {
        this.referenciaFactura = referenciaFactura;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("referenciaFactura", referenciaFactura)
            .toString();
    }
}
