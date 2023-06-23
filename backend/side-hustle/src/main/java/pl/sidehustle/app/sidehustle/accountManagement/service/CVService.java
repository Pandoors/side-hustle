package pl.sidehustle.app.sidehustle.accountManagement.service;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sidehustle.app.sidehustle.accountManagement.dto.CVDTO;
import pl.sidehustle.app.sidehustle.accountManagement.model.CV;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;
import pl.sidehustle.app.sidehustle.accountManagement.repository.CVRepository;
import pl.sidehustle.app.sidehustle.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CVService {
    Logger logger = LoggerFactory.getLogger(CVService.class);

    @Autowired
    private CVRepository cvRepository;

    public CVDTO getCVByUserId(Long userId) {
        return new CVDTO(cvRepository.getCVByUserId(userId));
    }

    public List<CVDTO> getCVsFromOffer(Long offerId) {
        return cvRepository.getCVsFromOffer(offerId).stream().map(CVDTO::new).collect(Collectors.toList());
    }

    public void addNewCv(CVDTO cvdto, User user) {

        if (cvdto != null) {

            CV cv = new CV(user.getId(), cvdto.getFirstName(), cvdto.getLastName(), cvdto.getEmail(), cvdto.getPhoneNumber(), cvdto.getEducation(), cvdto.getEmail(), user);
            cvRepository.addCV(cv);

        } else {
            throw new BadRequestException();
        }


    }

}
