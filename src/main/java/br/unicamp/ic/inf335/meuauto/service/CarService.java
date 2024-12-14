package br.unicamp.ic.inf335.meuauto.service;

import br.unicamp.ic.inf335.meuauto.dto.CompleteCarDTO;
import br.unicamp.ic.inf335.meuauto.entity.Car;
import br.unicamp.ic.inf335.meuauto.entity.User;
import br.unicamp.ic.inf335.meuauto.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createCar(User user, CompleteCarDTO carRequest){
        var newCar = new Car(UUID.randomUUID(), carRequest.value(), carRequest.brand(), carRequest.modelName(), carRequest.year(), carRequest.fuel(), user);

        carRepository.save(newCar);
    }

    public List<Car> listCarByOwner(User user){
        return carRepository.findByOwner_Id(user.getId());
    }


}
