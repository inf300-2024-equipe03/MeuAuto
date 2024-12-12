package br.unicamp.ic.inf335.meuauto.integration.tabelafipe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class TabelaFipeService {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.tabelafipe.url}")
    private String baseUrl;

    @Value("${api.tabelafipe.key}")
    private String apiKey;

    private final HttpHeaders headers;

    public TabelaFipeService() {
        headers = new HttpHeaders();
        headers.add("X-Subscription-Token", apiKey);
    }

    public List<BrandResponse> findBrands() {
        var url = baseUrl + "/carros/marcas";

        var requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create(url));

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<List<BrandResponse>>() {}
                ).getBody();
        }catch (Exception e){
            return List.of();
        }
    }

    public CarResponse findModelsByBrandId(String id){
        var url = baseUrl + "/carros/marcas/" + id + "/modelos";

        var requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create(url));

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<CarResponse>() {}
            ).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public List<ModelResponse> findVersionsByModelIdAndBrandId(String modelId, String brandId){
        var url = baseUrl + "/carros/marcas/" + brandId + "/modelos/" + modelId + "/anos";

        var requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create(url));

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<List<ModelResponse>>() {}
            ).getBody();
        }catch (Exception e){
            return List.of();
        }
    }

    public CompleteModelResponse findCarByBrandAndVersionAndModelId(String brandId, String modelId, String versionId) {
        var url = baseUrl + "/carros/marcas/" + brandId + "/modelos/" + modelId + "/anos/" + versionId;

        var requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create(url));

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<CompleteModelResponse>() {}
            ).getBody();
        }catch (Exception e){
            return null;
        }
    }
}
