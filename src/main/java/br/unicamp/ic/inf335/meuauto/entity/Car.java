package br.unicamp.ic.inf335.meuauto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "value")
    private String value;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model_description")
    private String modelDescription;

    @Column(name = "year")
    private String year;

    @Column(name = "fuel")
    private String fuel;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    public Car() {
    }

    public Car(UUID id, String value, String brand, String modelDescription, String year, String fuel, User owner) {
        this.id = id;
        this.value = value;
        this.brand = brand;
        this.modelDescription = modelDescription;
        this.year = year;
        this.fuel = fuel;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public String getYear() {
        return year;
    }

    public String getFuel() {
        return fuel;
    }

    public User getOwner() {
        return owner;
    }
}
