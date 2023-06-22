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

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Offer> offers;

    public String getFullName(){

        return city + " (" + name + ") " + address;

    }


    public Location() {
    }
}
