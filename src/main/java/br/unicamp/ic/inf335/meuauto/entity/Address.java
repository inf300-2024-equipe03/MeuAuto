package br.unicamp.ic.inf335.meuauto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "google_id", nullable = false)
    private String googleId;

    public Point getLocation() {
        return location;
    }

//    @Column(columnDefinition="GeomePointPointtry")
//    @Type("org.hibernate.spatial.GeometryType")
//    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "location", columnDefinition = "geography(Point, 4326)")
    private Point location;

    public Address(String description, String postalCode, String googleId, Point location) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.postalCode = postalCode;
        this.googleId = googleId;
        this.location = location;
    }
    public Address(UUID id, String description, String postalCode, String googleId, Point location) {
        this.id = id;
        this.description = description;
        this.postalCode = postalCode;
        this.googleId = googleId;
        this.location = location;
    }

    public Address() {
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

}
