package br.unicamp.ic.inf335.meuauto.service;

import br.unicamp.ic.inf335.meuauto.entity.Address;
import br.unicamp.ic.inf335.meuauto.entity.CarRepairShop;
import br.unicamp.ic.inf335.meuauto.integration.geocode.GeocodeService;
import br.unicamp.ic.inf335.meuauto.repository.AddressRepository;
import br.unicamp.ic.inf335.meuauto.repository.CarRepairShopRepository;
import com.google.maps.errors.ApiException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CarRepairShopService {

    private final CarRepairShopRepository carRepairShopRepository;

    private final GeocodeService geocodeService;

    private final AddressRepository addressRepository;
    private final GeometryFactory geometryFactory;

    public CarRepairShopService(CarRepairShopRepository carRepairShopRepository, GeocodeService geocodeService, AddressRepository addressRepository, GeometryFactory geometryFactory) {
        this.carRepairShopRepository = carRepairShopRepository;
        this.geocodeService = geocodeService;
        this.addressRepository = addressRepository;
        this.geometryFactory = geometryFactory;
    }

    public void createCarRepairShop(String name, double rating, String addressDescription) throws IOException, InterruptedException, ApiException {
        var googleAddress = Arrays.stream(
                geocodeService.findAddresByDescription(addressDescription)
        ).findFirst()
                .get();

        var postaCode = Arrays.stream(googleAddress.addressComponents)
                .filter(component ->
                        Arrays.stream(component.types).anyMatch(type -> type.name().equals("POSTAL_CODE"))
                ).findFirst().get().longName;

        var point = geometryFactory.createPoint(new Coordinate(googleAddress.geometry.location.lng, googleAddress.geometry.location.lat));
        var address = new Address(googleAddress.formattedAddress,postaCode, googleAddress.placeId, point);
        addressRepository.save(address);

        var carRepairShop = new CarRepairShop(UUID.randomUUID(), name, rating, address.getId());
        carRepairShopRepository.save(carRepairShop);
    }

    public List<CarRepairShop> listCarRepairShopNearUser(String userLocationDescription) throws IOException, InterruptedException, ApiException {
        var googleAddress = Arrays.stream(
                        geocodeService.findAddresByDescription(userLocationDescription)
                ).findFirst()
                .get();

        var postaCode = Arrays.stream(googleAddress.addressComponents)
                .filter(component ->
                        Arrays.stream(component.types).anyMatch(type -> type.name().equals("POSTAL_CODE"))
                ).findFirst().get().longName;

        var point = geometryFactory.createPoint(new Coordinate(googleAddress.geometry.location.lng, googleAddress.geometry.location.lat));
        var address = new Address(googleAddress.formattedAddress,postaCode, googleAddress.placeId, point);


        var addresses = addressRepository.findNearbyPlaces(address, 10, 0);
        var addressesIds = addresses.stream().map(Address::getId).toList();

        var result = carRepairShopRepository.findByAddessIdIn(addressesIds);

        return result;
    }
}
