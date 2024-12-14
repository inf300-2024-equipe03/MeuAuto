package br.unicamp.ic.inf335.meuauto.repository;

import br.unicamp.ic.inf335.meuauto.entity.CarRepairShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepairShopRepository extends JpaRepository<CarRepairShop, UUID> {

    List<CarRepairShop> findByAddessIdIn(Collection<UUID> addessIds);
}
