//package br.unicamp.ic.inf335.meuauto.entity;
//
//import br.unicamp.ic.inf335.meuauto.integration.tabelafipe.Car;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//import java.util.UUID;
//
//@Entity
//@Table(name = "raw_car")
//public class RawCar {
//
//    @Id
//    @Column(name = "id", nullable = false)
//    private UUID id;
//
//    @Column(name = "external_id", nullable = false)
//    private String externalId;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @JoinColumn(name = "brand_id")
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Brand brand;
//
//    public RawCar() {
//    }
//
//    public RawCar(UUID id, String externalId, String name, Brand brand) {
//        this.id = id;
//        this.externalId = externalId;
//        this.name = name;
//        this.brand = brand;
//    }
//
//    public RawCar(Car car, Brand brand) {
//        this(UUID.randomUUID(), car.codigo(), car.nome(), brand);
//    }
//
//    public Brand getBrand() {
//        return brand;
//    }
//
//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public String getExternalId() {
//        return externalId;
//    }
//
//    public void setExternalId(String externalId) {
//        this.externalId = externalId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
