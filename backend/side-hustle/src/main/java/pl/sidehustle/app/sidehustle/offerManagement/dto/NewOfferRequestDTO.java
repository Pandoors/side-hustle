package pl.sidehustle.app.sidehustle.offerManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.enums.JobType;
import pl.sidehustle.app.sidehustle.locationsManagement.dto.LocationDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@XmlRootElement(name = "")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class NewOfferRequestDTO {


    private BigDecimal wage;
    private String description;
    private String fullName;
    private String jobType;
    private String startDate;
    private String endDate;


    @XmlElement(name = "location_data")
    private LocationDTO location;


}
