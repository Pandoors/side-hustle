package pl.sidehustle.app.sidehustle.accountManagement.dto;

import lombok.Getter;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.accountManagement.model.CV;

@Getter
@Setter
public class CVDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String education;

    public CVDTO(String firstName, String lastName, String email, String phoneNumber, String education) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.education = education;
    }

    public CVDTO(CV cv) {
        this.firstName = cv.getName();
        this.lastName = cv.getSurname();
        this.email = cv.getEmail();
        this.phoneNumber = cv.getPhoneNumber();
        this.education = cv.getEducationalLevel();
    }
}
