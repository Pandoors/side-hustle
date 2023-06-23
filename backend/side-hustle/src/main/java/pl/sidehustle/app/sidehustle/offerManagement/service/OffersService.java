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
import pl.sidehustle.app.sidehustle.exceptions.BadRequestException;
import pl.sidehustle.app.sidehustle.locationsManagement.dto.LocationDTO;
import pl.sidehustle.app.sidehustle.locationsManagement.model.Location;
import pl.sidehustle.app.sidehustle.locationsManagement.repository.LocationRepository;
import pl.sidehustle.app.sidehustle.offerManagement.dto.EditOfferRequestDTO;
import pl.sidehustle.app.sidehustle.offerManagement.dto.NewOfferRequestDTO;
import pl.sidehustle.app.sidehustle.offerManagement.dto.OfferDTO;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;
import pl.sidehustle.app.sidehustle.offerManagement.repository.OfferRepository;
import pl.sidehustle.app.sidehustle.utils.DateUtil;

import java.text.ParseException;
import java.util.*;


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

    public List<OfferDTO> getOfferListPersonal(Integer offset, Integer size, User user, Role role) {

        if (role == null || !Set.of(RoleLevel.PROVIDER.toString(), RoleLevel.ADMIN.toString()).contains(role.getRoleLevel())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You need to be provider or admin in order to add offer");
        }

        List<OfferDTO> offers = offerRepository.getOffersPersonal(user.getId()).stream().map(OfferDTO::new).toList();

        if (offers == null || offers.isEmpty()) {
            return new ArrayList<>();
        }

        int length = offers.size();
        if (offset == null || size == null) {
            logger.info("Returning {} offers", this.defaultSize);

            if (this.defaultSize > length) {
                return offers.subList(0, length);
            } else {

                return offers.subList(0, this.defaultSize);
            }
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

    public void addNewOffer(NewOfferRequestDTO offerRequestDTO, User user, Role role) {

        if (role == null || !Set.of(RoleLevel.PROVIDER.toString(), RoleLevel.ADMIN.toString()).contains(role.getRoleLevel())) {
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

    public void editOffer(EditOfferRequestDTO offerRequestDTO, User user, Role role) throws ParseException {

        if (role == null || !Set.of(RoleLevel.PROVIDER.toString(), RoleLevel.ADMIN.toString()).contains(role.getRoleLevel())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You need to be provider or admin in order to add offer");
        }
        if (offerRequestDTO != null && offerRequestDTO.getOfferId() != null) {
            Offer offer = offerRepository.getOfferById(offerRequestDTO.getOfferId());
            Location location = offer.getLocation();
            if (offerRequestDTO.getLocation() != null) {
                LocationDTO locationDTO = offerRequestDTO.getLocation();
                if (locationDTO.getAddress() != null) {
                    location.setAddress(locationDTO.getAddress());
                }
                if (locationDTO.getCity() != null) {
                    location.setCity(locationDTO.getCity());
                }
                if (locationDTO.getDistrict() != null) {
                    location.setDistrict(locationDTO.getDistrict());
                }
                if (locationDTO.getLongitude() != null) {
                    location.setLongitude(locationDTO.getLongitude());
                }
                if (locationDTO.getLatitude() != null) {
                    location.setLatitude(locationDTO.getLatitude());
                }
            }
            locationRepository.editLocation(location);

            if (offerRequestDTO.getFullName() != null) {
                offer.setFullName(offerRequestDTO.getFullName());
            }
            if (offerRequestDTO.getEndDate() != null) {
                offer.setOfferEnd(DateUtil.parseDate(offerRequestDTO.getEndDate()));
            }
            if (offerRequestDTO.getStartDate() != null) {
                offer.setOfferStart(DateUtil.parseDate(offerRequestDTO.getStartDate()));
            }
            if (offerRequestDTO.getWage() != null) {
                offer.setPayment(offerRequestDTO.getWage());
            }
            if (offerRequestDTO.getDescription() != null) {
                offer.setDescription(offerRequestDTO.getDescription());
            }
            if (offerRequestDTO.getJobType() != null) {
                offer.setOfferType(offerRequestDTO.getJobType());
            }
            offerRepository.editOffer(offer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Offer is null or offer id is null!");
        }

    }

    public boolean appliedToOffer(Long offerId, Long userId) {
        return offerRepository.isOfferApplied(offerId, userId);
    }

    public void applyToOffer(Long offerId, Long userId) {
        offerRepository.applyToOffer(offerId, userId);
    }

    public void removeOffer(Long offerId, User user, Role role) {
        if (!role.getRoleLevel().equals(RoleLevel.PROVIDER.toString())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You need to be provider or admin in order to remove offer");
        }

        Offer offer = offerRepository.getOfferById(offerId);

        if (!Objects.equals(offer.getOwnerId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It's not Your offer");
        }

        offer.setDeletedAt(new Date());
        offerRepository.mergeOffer(offer);

    }

}
