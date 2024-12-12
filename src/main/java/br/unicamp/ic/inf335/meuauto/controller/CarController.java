package br.unicamp.ic.inf335.meuauto.controller;

import br.unicamp.ic.inf335.meuauto.dto.BrandResponseDTO;
import br.unicamp.ic.inf335.meuauto.dto.CompleteCarDTO;
import br.unicamp.ic.inf335.meuauto.dto.ModelResponseDTO;
import br.unicamp.ic.inf335.meuauto.dto.VersionResponseDTO;
import br.unicamp.ic.inf335.meuauto.entity.User;
import br.unicamp.ic.inf335.meuauto.integration.tabelafipe.TabelaFipeService;
import br.unicamp.ic.inf335.meuauto.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final TabelaFipeService tabelaFipeService;
    private final HttpServletRequest request;
    private final CarService carService;

    public CarController(TabelaFipeService tabelaFipeService, HttpServletRequest request, CarService carService) {
        this.tabelaFipeService = tabelaFipeService;
        this.request = request;
        this.carService = carService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponseDTO>> findBrands(){
        var brands = tabelaFipeService.findBrands();

        var result = brands
                .stream()
                .map(brand -> new BrandResponseDTO(brand.nome(), brand.codigo()))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/brands/{brand_id}")
    public ResponseEntity<List<ModelResponseDTO>> findModelsByBrand(
            @PathVariable("brand_id") String brandId
    ){
        var cars = tabelaFipeService.findModelsByBrandId(brandId);

        var result = cars.modelos()
                .stream()
                .map(brand -> new ModelResponseDTO(brand.nome(), brand.codigo()))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/brands/{brand_id}/model/{model_id}")
    public ResponseEntity<List<VersionResponseDTO>> findVersionByBrandAndModel(
            @PathVariable("brand_id") String brandId,
            @PathVariable("model_id") String modelId
    ){
        var models = tabelaFipeService.findVersionsByModelIdAndBrandId(modelId, brandId);

        var result = models
                .stream()
                .map(brand -> new VersionResponseDTO(brand.nome(), brand.codigo()))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/brands/{brand_id}/model/{model_id}/version/{version_id}")
    public ResponseEntity<CompleteCarDTO> findCarDetails(
            @PathVariable("brand_id") String brandId,
            @PathVariable("model_id") String modelId,
            @PathVariable("version_id") String versionId
    ){
        var car = tabelaFipeService.findCarByBrandAndVersionAndModelId(brandId, modelId, versionId);

        var result = new CompleteCarDTO(car.Valor(), car.Marca(), car.Modelo(), car.AnoModelo(), car.Combustivel(), null);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Object> createCar(
            @RequestBody CompleteCarDTO car
    ){
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        carService.createCar(user, car);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> listCarByOwner(
    ){
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var cars = carService.lisCarByOwner(user);

        var result = cars
                .stream()
                .map(car -> new CompleteCarDTO(
                        car.getValue(),
                        car.getBrand(),
                        car.getModelDescription(),
                        car.getYear(),
                        car.getFuel(),
                        car.getId())
                ).toList();

        return ResponseEntity.ok(result);
    }

}
