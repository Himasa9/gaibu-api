package com.example.gaibuapi;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    @RequestMapping( value="weather/Tokyo"
            , produces= MediaType.APPLICATION_JSON_VALUE
            , method= RequestMethod.GET)
    private String call() {

        RestTemplate rest = new RestTemplate();

        final String latitude = "35.6785";
        final String longitude = "139.6823";
        final String hourly = "temperature_2m";
        //utlにタイムゾーンを記載すると構文エラーが発生する。
        final String timezone = "Asia%2FTokyo";
        final String endpoint = "https://api.open-meteo.com/v1/forecast";

        final String url = endpoint + "?latitude=" + latitude + "&" + "longitude=" + longitude + "&"
                           + "hourly=" + hourly;

        ResponseEntity<String> response = rest.getForEntity(url, String.class);

        String json = response.getBody();

        return decode(json);
    }

    private static String decode(String string) {
        return StringEscapeUtils.unescapeJava(string);
    }
}
