package br.unicamp.ic.inf335.meuauto.controller;

import br.unicamp.ic.inf335.meuauto.dto.CarRepairShopRequest;
import br.unicamp.ic.inf335.meuauto.dto.CarRepairShopResponse;
import br.unicamp.ic.inf335.meuauto.integration.geocode.GeocodeService;
import br.unicamp.ic.inf335.meuauto.repository.AddressRepository;
import br.unicamp.ic.inf335.meuauto.repository.CarRepairShopRepository;
import br.unicamp.ic.inf335.meuauto.service.CarRepairShopService;
import com.google.maps.errors.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car_repair_shop")
public class CarRepairShopController {

    private final CarRepairShopService carRepairShopService;
    private final GeocodeService geocodeService;
    private final AddressRepository addressRepository;
    private final CarRepairShopRepository carRepairShopRepository;

    public CarRepairShopController(CarRepairShopService carRepairShopService, GeocodeService geocodeService, AddressRepository addressRepository, CarRepairShopRepository carRepairShopRepository) {
        this.carRepairShopService = carRepairShopService;
        this.geocodeService = geocodeService;
        this.addressRepository = addressRepository;
        this.carRepairShopRepository = carRepairShopRepository;
    }

    @GetMapping
    public ResponseEntity<List<CarRepairShopResponse>> listByUserLocations(
            @RequestParam(name = "location", required = true) String location
    ) throws IOException, InterruptedException, ApiException {
        var repairShops = carRepairShopService.listCarRepairShopNearUser(location);

        var result = new ArrayList<CarRepairShopResponse>();

        repairShops.forEach(repairShop -> {
            var repairShopAddress = addressRepository.findById(repairShop.getAddessId());
            try {
                var googleResult = geocodeService.caculateDistance(location,repairShopAddress.getDescription());
                result.add(new CarRepairShopResponse(repairShop.getId(), repairShop.getName(), repairShop.getRating(), googleResult.humanReadable, repairShopAddress.getDescription()));
            } catch (Exception e) {

            }
        });

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(
            @RequestBody CarRepairShopRequest carRepairShopRequest
    ) throws IOException, InterruptedException, ApiException {
        carRepairShopService.createCarRepairShop(carRepairShopRequest.name(), carRepairShopRequest.rating(), carRepairShopRequest.addressDescription());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarRepairShopResponse> findById(
            @PathVariable(name = "id") UUID id
    ){
        var repairShop = carRepairShopRepository.findById(id).get();
        var repairShopAddress = addressRepository.findById(repairShop.getAddessId());
        var result = new CarRepairShopResponse(repairShop.getId(), repairShop.getName(), repairShop.getRating(), "", repairShopAddress.getDescription());

        return ResponseEntity.ok(result);
    }
}
