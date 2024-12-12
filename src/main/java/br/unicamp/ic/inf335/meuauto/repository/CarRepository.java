package br.unicamp.ic.inf335.meuauto.repository;

import br.unicamp.ic.inf335.meuauto.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByOwner_Id(UUID id);
}
