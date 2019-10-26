package io.javafaktura.spring.boot.olx;

import io.javafaktura.spring.boot.olx.model.OlxProduct;
import io.javafaktura.spring.boot.scrapper.api.Scrapper;
import io.javafaktura.spring.boot.scrapper.api.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

/**
 * @author Adam Król 2019-10-19
 */

@Component
public class OlxScrapper implements Scrapper {

    private static final String URL_TEMPLATE = "https://www.olx.pl/oferty/q-%s/";

    @Override
    public List<Product> findByPhrase(String phrase) {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest searchRequest = HttpRequest.newBuilder(URI.create(String.format(URL_TEMPLATE, URLEncoder.encode(phrase, UTF_8))))
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
        var elements = page.select("table.offers tbody tr.wrap");
        return elements.stream().map(element -> {
            var title = element.select("td.title-cell h3 a strong").text();
            var price = Double.parseDouble(element.select("p.price").text().replaceAll(" |zł", ""));
            var city = element.select("i[data-icon='location-filled']").parents().first().text();
            return new OlxProduct(title, price, city);
        }).collect(toList());
    }
}
