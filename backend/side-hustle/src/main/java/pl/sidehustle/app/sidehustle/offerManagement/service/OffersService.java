package pl.sidehustle.app.sidehustle.offerManagement.service;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.sidehustle.app.sidehustle.accountManagement.model.Role;
import pl.sidehustle.app.sidehustle.accountManagement.model.RoleLevel;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;
import pl.sidehustle.app.sidehustle.accountManagement.repository.UserRepository;
import pl.sidehustle.app.sidehustle.enums.JobType;
import pl.sidehustle.app.sidehustle.exceptions.BadRequestException;
import pl.sidehustle.app.sidehustle.locationsManagement.dto.LocationDTO;
import pl.sidehustle.app.sidehustle.locationsManagement.model.Location;
import pl.sidehustle.app.sidehustle.locationsManagement.repository.LocationRepository;
import pl.sidehustle.app.sidehustle.offerManagement.dto.NewOfferRequestDTO;
import pl.sidehustle.app.sidehustle.offerManagement.dto.OfferDTO;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;
import pl.sidehustle.app.sidehustle.offerManagement.repository.OfferRepository;
import pl.sidehustle.app.sidehustle.utils.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service
@NoArgsConstructor
public class OffersService {
    private int defaultSize = 10;
    private int maxSize = 10;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private LocationRepository locationRepository;


    Logger logger = LoggerFactory.getLogger(OffersService.class);


    /**
     * Dummy method for now.
     *
     * @param offset how many records to skip
     * @param size   how many records to fetch
     * @return list of offer DTOs fetched from db but there's no db right now
     */
    public List<OfferDTO> getOfferList(Integer offset, Integer size) {
        List<OfferDTO> offers = offerRepository.getOffers().stream().map(OfferDTO::new).toList();
        int length = offers.size();
        if (offset == null || size == null) {
            logger.info("Returning {} offers", this.defaultSize);
            return offers.subList(0, this.defaultSize);

        } else if (offset > length || size > length || offset + size > length) {
            logger.error("Bad query params in getOfferList");
            throw new BadRequestException();

        } else {
            logger.info("Returning {} offers", size);
            return offers.subList(offset, offset + size);
        }
    }


    public Integer getOffersCount() {
        return offerRepository.getOffers().size();
    }

    public List<OfferDTO> getOwnersOffers(Long ownerId) {
        return offerRepository.getOffersByOwnerId(ownerId).stream().map(OfferDTO::new).toList();
    }

    public OfferDTO getOfferById(Long id) {
        return new OfferDTO(offerRepository.getOfferById(id));
    }

    public List<OfferDTO> getOffersByLocationId(Long locationId) {
        return offerRepository.getOffersByLocationId(locationId).stream().map(OfferDTO::new).toList();
    }

    public void addNewOffer(NewOfferRequestDTO offerRequestDTO , User user, Role role){

        if (role == null || !Set.of(RoleLevel.PROVIDER.toString(), RoleLevel.ADMIN.toString()).contains(role.getRoleLevel())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You need to be provider or admin in order to add offer");
        }

        if (offerRequestDTO.getLocation() != null) {
            LocationDTO locationDTO = offerRequestDTO.getLocation();
            Location location = new Location(
                    locationDTO.getLongitude(),
                    locationDTO.getLatitude(),
                    locationDTO.getCity(),
                    locationDTO.getAddress(),
                    locationDTO.getDistrict());
            locationRepository.addLocation(location);

            Offer offer = null;
            try {
                offer = new Offer(
                        offerRequestDTO.getFullName(),
                        offerRequestDTO.getJobType(),
                        offerRequestDTO.getDescription(),
                        offerRequestDTO.getWage(),
                        location,
                        user,
                        DateUtil.parseDate(offerRequestDTO.getStartDate()),
                        DateUtil.parseDate(offerRequestDTO.getEndDate()),
                        location.getId(),
                        user.getId()
                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            offerRepository.addOffer(offer);

        } else {
            throw new BadRequestException();
        }

    }

}
