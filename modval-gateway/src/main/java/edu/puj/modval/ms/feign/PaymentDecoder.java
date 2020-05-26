package edu.puj.modval.ms.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.puj.modval.ms.dto.PaymentDTO;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class PaymentDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        /*if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new Exception("not found");
        }*/

        //Response.Body body = response.body();

        InputStream is = response.body().asInputStream();
        byte[] body = Util.toByteArray(is);
        if (body == null) return null;

        if (PaymentDTO.class.equals(type)) {
            //var reader = body.asReader(StandardCharsets.UTF_8);/
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(body, PaymentDTO.class);
        }
        throw new DecodeException(response.status(), String.format("%s is not a type supported by this decoder. ", type), response.request());
    }
}
