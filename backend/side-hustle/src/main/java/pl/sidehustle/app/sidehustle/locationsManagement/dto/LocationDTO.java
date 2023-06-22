package pl.sidehustle.app.sidehustle.locationsManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationDTO {

    private String city;
    private String address;
    private String district;
    private String latitude;
    private String longitude;

    public LocationDTO(String city, String address, String district, String latitude, String longitude) {
        this.city = city;
        this.address = address;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
