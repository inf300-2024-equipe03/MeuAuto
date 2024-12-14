package br.unicamp.ic.inf335.meuauto.controller;

import br.unicamp.ic.inf335.meuauto.entity.CarRepairShop;
import br.unicamp.ic.inf335.meuauto.service.CarRepairShopService;
import com.google.maps.errors.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/car_repair_shop")
public class CarRepairShopController {

    private final CarRepairShopService carRepairShopService;

    public CarRepairShopController(CarRepairShopService carRepairShopService) {
        this.carRepairShopService = carRepairShopService;
    }

    @GetMapping
    public ResponseEntity<List<CarRepairShop>> listByUserLocations(
            @RequestParam(name = "location", required = true) String location
    ) throws IOException, InterruptedException, ApiException {
        var result = carRepairShopService.listCarRepairShopNearUser(location);

        return ResponseEntity.ok(result);
    }
}
