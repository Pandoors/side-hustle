package pl.sidehustle.app.sidehustle.offerManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.locationsManagement.dto.LocationDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@XmlRootElement(name = "")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class NewOfferRequestDTO {


    private Double wage;
    private String description;
    private String fullName;
    private String jobType;
    private String startDate;
    private String endDate;
    private String hourStart;
    private String hourEnd;
    private String estimatedDuration;
    @XmlElement(name = "location_data")
    private LocationDTO location;


    public NewOfferRequestDTO(Double wage, String description, String fullName, String jobType, String startDate, String endDate, String hourStart, String hourEnd, String estimatedDuration, LocationDTO location) {
        this.wage = wage;
        this.description = description;
        this.fullName = fullName;
        this.jobType = jobType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.estimatedDuration = estimatedDuration;
        this.location = location;
    }
}
