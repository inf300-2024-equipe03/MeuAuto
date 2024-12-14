package br.unicamp.ic.inf335.meuauto.controller;

import br.unicamp.ic.inf335.meuauto.entity.Address;
import br.unicamp.ic.inf335.meuauto.integration.geocode.GeocodeService;
import br.unicamp.ic.inf335.meuauto.integration.tabelafipe.TabelaFipeService;
import br.unicamp.ic.inf335.meuauto.repository.AddressRepository;
import com.google.maps.errors.ApiException;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@RestController
@RequestMapping("geo_code")
public class GeocodeController {

    private final GeocodeService geocodeService;
    private final AddressRepository addressRepository;
    private final GeometryFactory geometryFactory;
    private final TabelaFipeService tabelaFipeService;


    public GeocodeController(GeocodeService geocodeService, AddressRepository addressRepository, GeometryFactory geometryFactory, TabelaFipeService tabelaFipeService) {
        this.geocodeService = geocodeService;
        this.addressRepository = addressRepository;
        this.geometryFactory = geometryFactory;
        this.tabelaFipeService = tabelaFipeService;
    }

    @GetMapping
    public ResponseEntity<String> getGeocode(
            @RequestParam(name = "description", required = false) String description
    ) throws IOException, InterruptedException, ApiException {
        var result = Arrays.stream(geocodeService.findAddresByDescription(description)).findFirst().get();

        var postaCode = Arrays.stream(result.addressComponents)
                .filter(component ->
                        Arrays.stream(component.types).anyMatch(type -> type.name().equals("POSTAL_CODE"))
                ).findFirst().get().longName;

        var point = geometryFactory.createPoint(new Coordinate(result.geometry.location.lng, result.geometry.location.lat));
        var address = new Address(result.formattedAddress,"13044500", result.placeId, point);
        addressRepository.save(address);

        var distanceResult = geocodeService.caculateDistance("rua antonio marques serra campinas", "avenida moraes salles campinas");


        var brand = tabelaFipeService.findBrands().stream().findFirst().get();
        var car = tabelaFipeService.findModelsByBrandId(brand.codigo()).modelos().stream().findFirst().get();
        var carModel = tabelaFipeService.findVersionsByModelIdAndBrandId(car.codigo(), brand.codigo()).stream().findFirst().get();
        var completeModel = tabelaFipeService.findCarByBrandAndVersionAndModelId(brand.codigo(), car.codigo(), carModel.codigo());

        return ResponseEntity.ok("OK");
    }
}
