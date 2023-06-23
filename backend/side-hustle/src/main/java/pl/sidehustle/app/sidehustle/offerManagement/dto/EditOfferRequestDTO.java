package pl.sidehustle.app.sidehustle.offerManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class EditOfferRequestDTO extends NewOfferRequestDTO{

    private Long offerId;

    public EditOfferRequestDTO(Long offerId) {
        super();
        this.offerId = offerId;
    }
}
