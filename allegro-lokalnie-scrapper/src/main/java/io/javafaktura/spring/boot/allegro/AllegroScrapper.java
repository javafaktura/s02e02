package io.javafaktura.spring.boot.allegro;

import io.javafaktura.spring.boot.allegro.model.AllegroProduct;
import io.javafaktura.spring.boot.scrapper.api.Scrapper;
import io.javafaktura.spring.boot.scrapper.api.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

/**
 * @author Adam Król 2019-10-19
 */
@Component
public class AllegroScrapper implements Scrapper {

    private static final String URL_TEMPLATE = "https://allegrolokalnie.pl/oferty/?phrase=%s";

    private final int fetchSize;

    public AllegroScrapper(@Value("${allegro.fetch-size}") int fetchSize) {
        this.fetchSize = fetchSize;
        checkAllegro();
    }

    private void checkAllegro() {
        try {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("allegrolokalnie.pl", 443), 1000);
            }
        } catch (IOException exception) {
            throw new UnableToSetupAllegroException("Allegro lokalnie is not available", exception);
        }
    }

    @Override
    public List<Product> findByPhrase(String phrase) {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest searchRequest = HttpRequest.newBuilder(URI.create(String.format(URL_TEMPLATE, encode(phrase, UTF_8))))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(searchRequest, HttpResponse.BodyHandlers.ofString());
            return parseBody(response.body());
        } catch (Exception e) {
            return List.of();
        }
    }

    private List<Product> parseBody(String bodyPublisher) {
        Document page = Jsoup.parse(bodyPublisher);
        var elements = page.select("div.offer-card");
        return elements.stream().map(element -> {
            var title = element.select("h3").text();
            var price = Double.parseDouble(element.select("span.price").text()
                    .replaceAll(" |zł", "")
                    .replace(",", "."));
            var city = element.select("span[itemprop='address']").text();
            return new AllegroProduct(title, price, city);
        }).limit(fetchSize).collect(toList());
    }
}
