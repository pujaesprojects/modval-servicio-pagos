package edu.puj.modval.ms.config;

import edu.puj.modval.ms.feign.CustomSOAPEncoder;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapClientConfiguration {
    @Bean
    public Encoder feignEncoder(JAXBContextFactory jaxbFactory) {
        return new CustomSOAPEncoder(jaxbFactory);
    }
    @Bean
    public Decoder feignDecoder(JAXBContextFactory jaxbFactory) {
        return new SOAPDecoder(jaxbFactory);
    }

    @Bean
    public JAXBContextFactory jaxbContextFactory() {
        return new JAXBContextFactory.Builder()
                .withMarshallerJAXBEncoding("UTF-8")
                .withMarshallerFormattedOutput(true)
                .build();
    }
}
