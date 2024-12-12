//package br.unicamp.ic.inf335.meuauto.entity;
//
//import br.unicamp.ic.inf335.meuauto.integration.tabelafipe.ModelResponse;
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
//@Table(name = "raw_model")
//public class RawModel {
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
//    @JoinColumn(name = "base_car_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private RawCar baseCar;
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
//
//    public RawModel() {
//    }
//
//    public RawModel(ModelResponse modelResponse, RawCar baseCar) {
//        this(UUID.randomUUID(), modelResponse.codigo(), modelResponse.nome(), baseCar);
//    }
//
//    public RawModel(UUID id, String externalId, String name, RawCar baseCar) {
//        this.id = id;
//        this.externalId = externalId;
//        this.name = name;
//        this.baseCar = baseCar;
//    }
//}
