//package br.unicamp.ic.inf335.meuauto.service;
//
//import br.unicamp.ic.inf335.meuauto.entity.BaseCar;
//import br.unicamp.ic.inf335.meuauto.entity.RawCar;
//import br.unicamp.ic.inf335.meuauto.entity.Brand;
//import br.unicamp.ic.inf335.meuauto.entity.RawModel;
//import br.unicamp.ic.inf335.meuauto.integration.tabelafipe.TabelaFipeService;
//import br.unicamp.ic.inf335.meuauto.repository.RawCarRepository;
//import br.unicamp.ic.inf335.meuauto.repository.BrandRepository;
//import br.unicamp.ic.inf335.meuauto.repository.BaseCarRepository;
//import br.unicamp.ic.inf335.meuauto.repository.RawModelRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.net.URISyntaxException;
//
//@Component
//public class CarScheduler {
//
//    private final RawModelRepository rawModelRepository;
//
//    private final BaseCarRepository baseCarRepository;
//
//    private final BrandRepository brandRepository;
//
//    private final RawCarRepository rawCarRepository;
//
//    private final TabelaFipeService tabelaFipeService;
//
//    @Value("${param.import-cars}")
//    private Boolean importCars;
//
//    public CarScheduler(RawModelRepository rawModelRepository, BaseCarRepository baseCarRepository, BrandRepository brandRepository, RawCarRepository rawCarRepository, TabelaFipeService tabelaFipeService) {
//        this.rawModelRepository = rawModelRepository;
//        this.baseCarRepository = baseCarRepository;
//        this.brandRepository = brandRepository;
//        this.rawCarRepository = rawCarRepository;
//        this.tabelaFipeService = tabelaFipeService;
//    }
//
//
////    @Scheduled(cron = "0 */2 * * * *")
//    public void importCars() throws URISyntaxException {
//
//        var brands = tabelaFipeService.findBrands();
//        for (var brand : brands) {
//            var newBrand = new Brand(brand);
//            brandRepository.save(newBrand);
//
//            var carsByBrand = tabelaFipeService.findCarByBrandId(brand.codigo());
//            for (var car: carsByBrand.modelos()){
//                RawCar newRawCar = new RawCar(car, newBrand);
//                rawCarRepository.save(newRawCar);
//
//                var modelsByCar = tabelaFipeService.findModelsByCarIdAndBrandId(car.codigo(), brand.codigo());
//                for (var rawModel: modelsByCar){
//                    RawModel newRawModel = new RawModel(rawModel, newRawCar);
//                    rawModelRepository.save(newRawModel);
//
//                    var completeModel = tabelaFipeService.findModelDetailsByBrandAndCarAndModelId(newBrand.getExternalId(), newRawCar.getExternalId(), newRawModel.getExternalId());
//                    var newBaseCar = new BaseCar(completeModel, newRawModel);
//                    baseCarRepository.save(newBaseCar);
//
//                }
//            }
//        }
//
//    }
//}
