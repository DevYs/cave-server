package devy.cave.server.search.parser;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DocumentParser {

    private final Logger logger = LoggerFactory.getLogger(DocumentParser.class);

    public Object parse(String url, MultiValueMap<String, String> param, IParser iParser) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, param, String.class);
        String html = stringResponseEntity.toString();
        return iParser.parse(Jsoup.parse(html));
    }

    public Object parseHtml(String url, IParser iParser) {
        return iParser.parse(Jsoup.parse(request(url)));
    }

    public Object parseJson(String url, IParser iParser) {
        return iParser.parse(request(url));
    }

    public String request(String url) {
        HttpResponse result = null;
        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri(url)
                .setHeader(HttpHeaders.CONTENT_TYPE, "text/*")
                .build();
        InputStream is = null;
        BufferedInputStream bis = null;
        int size = 10240;
        String body = null;

        try {
            result = client.execute(request);

            is = result.getEntity().getContent();
            bis = new BufferedInputStream(is);
            byte[] buffer = new byte[size];

            while ((bis.read(buffer)) != -1) {
                body += new String(buffer, "utf-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return body;
    }

}