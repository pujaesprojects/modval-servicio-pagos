package edu.puj.modval.ms.feign.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
    name = "Resultado",
    namespace = "http://www.servicios.co/pagos/schemas"
)
public class PaymentResponse {
    private BalanceRequest referenciaFactura;
    private String mensaje;

    @XmlElement(namespace = "http://www.servicios.co/pagos/schemas")
    public BalanceRequest getReferenciaFactura() {
        return referenciaFactura;
    }

    public void setReferenciaFactura(BalanceRequest referenciaFactura) {
        this.referenciaFactura = referenciaFactura;
    }

    @XmlElement(namespace = "http://www.servicios.co/pagos/schemas")
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("referenciaFactura", referenciaFactura)
            .append("mensaje", mensaje)
            .toString();
    }
}
