package pl.sidehustle.app.sidehustle.accountManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.accountManagement.model.CV;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class CVDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String education;


    public CVDTO() {
    }

    public CVDTO(CV cv) {
        this.firstName = cv.getName();
        this.lastName = cv.getSurname();
        this.email = cv.getEmail();
        this.phoneNumber = cv.getPhoneNumber();
        this.education = cv.getEducationalLevel();
    }
}
