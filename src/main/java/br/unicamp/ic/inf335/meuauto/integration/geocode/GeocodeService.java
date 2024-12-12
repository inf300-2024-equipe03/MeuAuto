package br.unicamp.ic.inf335.meuauto.integration.geocode;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.Distance;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class GeocodeService {
    private final GeoApiContext geoApiContext;

    public GeocodeService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public GeocodingResult[] findAddresByDescription(String description) throws IOException, InterruptedException, ApiException {
        return GeocodingApi
                .newRequest(this.geoApiContext)
                .components(ComponentFilter.locality(description))
                .await();
    }

    public GeocodingResult[] findAddresByPostalCode(String postalCode) throws IOException, InterruptedException, ApiException {
        return GeocodingApi
                .newRequest(this.geoApiContext)
                .components(ComponentFilter.postalCode(postalCode))
                .await();
    }

    public Distance caculateDistance(String origin, String destination) throws IOException, InterruptedException, ApiException {
        return Arrays.stream(
                Arrays.stream(
                        DistanceMatrixApi
                                .newRequest(this.geoApiContext)
                                .origins(origin)
                                .destinations(destination)
                                .await().rows
                ).findFirst().get().elements
        ).findFirst().get().distance;
    }

    public Distance caculateDistance(LatLng origin, LatLng destination) throws IOException, InterruptedException, ApiException {
        return Arrays.stream(
                Arrays.stream(
                        DistanceMatrixApi
                                .newRequest(this.geoApiContext)
                                .origins(origin)
                                .destinations(destination)
                                .await().rows
                ).findFirst().get().elements
        ).findFirst().get().distance;
    }
}
