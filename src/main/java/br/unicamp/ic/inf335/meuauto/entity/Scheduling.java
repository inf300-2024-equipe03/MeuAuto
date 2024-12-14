package br.unicamp.ic.inf335.meuauto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "schedulings")
public class Scheduling {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @JoinColumn(name = "car_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @JoinColumn(name = "car_repair_shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CarRepairShop carRepairShop;

    @Column(name = "scheduled_for")
    private LocalDateTime scheduledFor;



    public Scheduling() {
    }

    public Scheduling(UUID id, User owner, Car car, LocalDateTime scheduledFor, CarRepairShop carRepairShop) {
        this.id = id;
        this.owner = owner;
        this.car = car;
        this.scheduledFor = scheduledFor;
        this.carRepairShop = carRepairShop;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(LocalDateTime scheduledFor) {
        this.scheduledFor = scheduledFor;
    }

    public CarRepairShop getCarRepairShop() {
        return carRepairShop;
    }

    public void setCarRepairShop(CarRepairShop carRepairShop) {
        this.carRepairShop = carRepairShop;
    }
}
