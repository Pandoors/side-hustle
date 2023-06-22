package pl.sidehustle.app.sidehustle.locationsManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "locations")
@Access(AccessType.FIELD)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "district")
    private String district;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Offer> offers;

    public String getFullName(){

        return city + " (" + district + ") " + address;

    }

    public Location(Double longitude, Double latitude, String city, String address, String district) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.address = address;
        this.district = district;
        this.createdAt = new Date();
    }

    public Location() {
    }
}
