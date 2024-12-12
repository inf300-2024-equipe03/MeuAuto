package br.unicamp.ic.inf335.meuauto.config.geocode;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeocodeyFactory {

    @Value("${api.geocode.key}")
    private String apiKey;

    @Bean
    public GeoApiContext geocodingApi() {
        return new GeoApiContext.Builder().apiKey(apiKey).build();
    }
}
