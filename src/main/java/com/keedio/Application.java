package com.keedio;

import com.keedio.domain.AdmAccessToken;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Example app showing the usage of Azure Speech-to-text REST API.
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Application entry point, pass the "auth" string as an argument to force authentication against MS server.
     * <p>
     * Pass the path to the wav file to call the Microsoft conversion API.
     *
     * @param args
     * @throws Exception
     */
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

            Map<String, String> c = convert(audioFile);

            if (c != null)
                log.info(c.toString());
        }
    }

    /**
     * Call the conversion API.
     * <p>
     * Check out MS documentation at:
     * https://onedrive.live.com/view.aspx?resid=9A8C02C3B59E575!106&ithint=file%2cdocx&app=Word&authkey=!AIEJaNeh8CcDTjU
     * <p>
     * (you need an Azure account to acces the above file).
     * <p>
     * Relies on the existence of a BING_AUTH environment variable to verify the current device has been properly authenticated.
     *
     * @param audioFile the absolute path of the wav file to convert.
     * @return a {@see Conversion} object encapsulating the info returned by the Microsoft API.
     * @throws IOException
     */
    private HashMap<String, String> convert(String audioFile) throws IOException {
        String auth = System.getenv("BING_AUTH");

        log.info("Auth:\n" + auth);

        String authToken = "Bearer " + auth;

        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_XML);
        mediaTypes.add(MediaType.APPLICATION_JSON);

        headers.setAccept(mediaTypes);

        headers.add(HttpHeaders.AUTHORIZATION, authToken);
        headers.add(HttpHeaders.HOST, "speech.platform.bing.com");
        headers.set(HttpHeaders.CONTENT_TYPE, "audio/wav;codec=\"audio/pcm\";samplerate=8000;trustsourcerate=false");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<byte[]> entity = new HttpEntity<>(IOUtils.toByteArray(new FileInputStream(audioFile)), headers);

        String requestId = UUID.randomUUID().toString();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://speech.platform.bing.com/recognize/query")
                .queryParam("format", "json")
                .queryParam("appid", "D4D52672-91D7-4C74-8AD8-42B1D98141A5")
                .queryParam("locale", "es-ES")
                .queryParam("device.os", "Linux")
                .queryParam("version", "3.0")
                .queryParam("maxnbest", "3")
                .queryParam("scenarios", "smd")
                .queryParam("instanceid", testAppDeviceId)
                .queryParam("requestid", requestId);

        HashMap<String,String> dummy = new HashMap<>();

        ResponseEntity<HashMap<String,String>> conversion =
                restTemplate.exchange(builder.build().encode().toUri(),
                        HttpMethod.POST, entity, (Class<HashMap<String,String>>) dummy.getClass());

        return conversion.getBody();
    }

    /**
     * Authentication helper method.
     *
     * @return
     * @throws UnsupportedEncodingException
     */
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

        log.info("export BING_AUTH=\"" + result.getBody().getAccess_token() + "\"");

        return result;
    }
}
