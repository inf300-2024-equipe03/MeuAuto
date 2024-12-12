//package br.unicamp.ic.inf335.meuauto.entity;
//
//import br.unicamp.ic.inf335.meuauto.integration.tabelafipe.BrandResponse;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//import java.util.UUID;
//
//@Entity
//@Table(name = "brand")
//public class Brand {
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
//    public Brand(UUID id, String externalId, String name) {
//        this.id = id;
//        this.externalId = externalId;
//        this.name = name;
//    }
//
//    public Brand(){}
//
//    public Brand(BrandResponse brandResponse) {
//        this(UUID.randomUUID(), brandResponse.codigo(), brandResponse.nome());
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
//
//
//}
