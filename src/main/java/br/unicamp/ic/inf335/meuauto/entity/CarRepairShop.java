package br.unicamp.ic.inf335.meuauto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "car_repair_shops")
public class CarRepairShop {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private double rating;

    @Column(name = "address_id")
    private UUID addessId;

    public CarRepairShop() {
    }

    public CarRepairShop(UUID id, String name, double rating, UUID addessId) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.addessId = addessId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public UUID getAddessId() {
        return addessId;
    }

    public void setAddessId(UUID addessId) {
        this.addessId = addessId;
    }
}
