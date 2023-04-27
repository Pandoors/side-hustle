package pl.sidehustle.app.sidehustle.offerManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
//@NoArgsConstructor
public class OfferDTO {

    private Long offerId;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private String jobType;
    private String formattedDuration;
    private String wage;
    private Double longitude;
    private Double latitude;

    public OfferDTO(Long offerId, String description, String location, String startDate, String endDate, String jobType, String formattedDuration, String wage, Double longitude, Double latitude) {
        this.offerId = offerId;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobType = jobType;
        this.formattedDuration = formattedDuration;
        this.wage = wage;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
