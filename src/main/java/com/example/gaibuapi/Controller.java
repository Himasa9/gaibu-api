package com.example.gaibuapi;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class Controller {
    @RequestMapping(value = "weather/Tokyo"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , method = RequestMethod.GET)
    private String call() {

        RestTemplate rest = new RestTemplate();

        final String latitude = "35.6785";
        final String longitude = "139.6823";
        final String hourly = "temperature_2m";
        final String timezone = "Asia/Tokyo";
        final String endpoint = "https://api.open-meteo.com/v1/forecast";

        final URI uri = URI.create(encode(endpoint + "?latitude=" + latitude + "&" + "longitude=" + longitude + "&"
                + "hourly=" + hourly + "&" + "timezone=" + timezone));


        ResponseEntity<String> response = rest.getForEntity(uri, String.class);

        String json = response.getBody();

        return decode(json);
    }

    private static String decode(String string) {
        return StringEscapeUtils.unescapeJava(string);
    }

    @Deprecated
    public static String encode(String string){
        return StringEscapeUtils.unescapeJava(string);
    }
}
