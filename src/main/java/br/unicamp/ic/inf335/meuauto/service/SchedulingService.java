package br.unicamp.ic.inf335.meuauto.service;

import br.unicamp.ic.inf335.meuauto.entity.Scheduling;
import br.unicamp.ic.inf335.meuauto.entity.User;
import br.unicamp.ic.inf335.meuauto.repository.CarRepairShopRepository;
import br.unicamp.ic.inf335.meuauto.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final CarRepairShopRepository repairShopRepository;
    private final CarService carService;

    public SchedulingService(SchedulingRepository schedulingRepository, CarRepairShopRepository repairShopRepository, CarService carService) {
        this.schedulingRepository = schedulingRepository;
        this.repairShopRepository = repairShopRepository;
        this.carService = carService;
    }

    public void create(User user, UUID repairShopId, LocalDateTime dateTime){
        var car = carService.listCarByOwner(user).get(0);

        var repairShop = repairShopRepository.findById(repairShopId).get();

        var scheduling = new Scheduling(UUID.randomUUID(), user, car, dateTime, repairShop);
        schedulingRepository.save(scheduling);
    }

    public List<Scheduling> listByUser(User user){
        return schedulingRepository.findByOwner_Id(user.getId());
    }
}
