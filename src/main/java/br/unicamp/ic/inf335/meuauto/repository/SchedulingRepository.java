package br.unicamp.ic.inf335.meuauto.repository;

import br.unicamp.ic.inf335.meuauto.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {
    List<Scheduling> findByOwner_Id(UUID id);
}
