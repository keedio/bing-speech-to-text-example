package com.keedio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.keedio.domain.AdmAccessToken;
import com.keedio.domain.Conversion;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Luca Rosellini <lrosellini@keedio.com> on 6/11/15.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static final String DATAMARKET_ACCESS_URI = "https://datamarket.accesscontrol.windows.net/v2/OAuth2-13";
    public static final String SCOPE = "https://speech.platform.bing.com";

    @Value("${bing.speech.to.text.example.app.id}")
    private String testAppId;

    @Value("${bing.speech.to.text.example.app.secret}")
    private String testAppSecret;

    @Value("${bing.speech.to.text.example.device.id}")
    private String testAppDeviceId;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String auth = null;
        try {
            auth = args[0];
        } catch (Exception e) {
        }

        if ("auth".equals(auth)) {
            authenticate();

        } else {
            String audioFile = args[0];

            Conversion c = convert(audioFile);

            if (c != null)
                log.info(c.getResults().toString());
        }
    }

    private Conversion convert(String audioFile) throws IOException {
        String auth;
        File file = new File(audioFile);

        auth = System.getenv("BING_AUTH");

        log.info("Auth:\n" + auth);

        String authToken = "Bearer " + auth ;

        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_XML);
        mediaTypes.add(MediaType.APPLICATION_JSON);

        headers.setAccept(mediaTypes);

        headers.add(HttpHeaders.AUTHORIZATION, authToken);
        headers.add(HttpHeaders.HOST,"speech.platform.bing.com");
        headers.set(HttpHeaders.CONTENT_TYPE,"audio/wav;codec=\"audio/pcm\";samplerate=8000");
        headers.set(HttpHeaders.CONTENT_LENGTH,""+file.length());

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<byte[]> entity = new HttpEntity<>(IOUtils.toByteArray(new FileInputStream(audioFile)),headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://speech.platform.bing.com/recognize/query")
                .queryParam("format", "json")
                .queryParam("appid", "D4D52672-91D7-4C74-8AD8-42B1D98141A5")
                .queryParam("locale", "es-ES")
                .queryParam("device.os", "Linux")
                .queryParam("version", "3.0")
                .queryParam("scenarios", "smd")
                .queryParam("instanceid", testAppDeviceId)
                .queryParam("requestid", UUID.randomUUID().toString());

        ResponseEntity<Conversion> conversion =
                restTemplate.exchange(builder.build().encode().toUri(),
                        HttpMethod.POST, entity, Conversion.class);

        return conversion.getBody();
    }

    private ResponseEntity<AdmAccessToken> authenticate() throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();

        String body =
                String.format("grant_type=client_credentials&client_id=%s&client_secret=%s&scope=%s",
                        URLEncoder.encode(testAppId, "UTF-8"),
                        URLEncoder.encode(testAppSecret, "UTF-8"),
                        URLEncoder.encode(SCOPE, "UTF-8"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<AdmAccessToken> result =
                restTemplate.exchange(DATAMARKET_ACCESS_URI, HttpMethod.POST, entity, AdmAccessToken.class);

        log.info("export BING_AUTH=\""+result.getBody().getAccess_token()+ "\"");

        return result;
    }
}
