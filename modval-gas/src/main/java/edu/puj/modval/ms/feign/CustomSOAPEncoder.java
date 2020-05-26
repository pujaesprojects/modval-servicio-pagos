package edu.puj.modval.ms.feign;

import feign.Headers;
import feign.RequestTemplate;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPEncoder;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * Custom SOAP Encoder, añade funcionalidad para agregar headers a la petición.
 * Útil para agregar el header SOAPAction.
 */
public class CustomSOAPEncoder extends SOAPEncoder {
    public CustomSOAPEncoder(JAXBContextFactory jaxbContextFactory) {
        super(jaxbContextFactory);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        super.encode(object, bodyType, template);
        Method method = template.methodMetadata().method();
        try {
            Headers headers = method.getDeclaredAnnotation(Headers.class);
            Stream.of(headers.value())
                .forEach(h -> {
                    String[] header = h.split(":");
                    if(header.length == 2) {
                        template.header(header[0], header[1].trim());
                    }
                });
        } catch (Exception ignored) {}
    }
}
