package pl.sidehustle.app.sidehustle.offerManagement.controller.v1;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.sidehustle.app.sidehustle.accountManagement.model.Role;
import pl.sidehustle.app.sidehustle.accountManagement.model.RoleLevel;
import pl.sidehustle.app.sidehustle.offerManagement.dto.NewOfferRequestDTO;
import pl.sidehustle.app.sidehustle.accountManagement.dto.CVDTO;
import pl.sidehustle.app.sidehustle.accountManagement.service.CVService;
import pl.sidehustle.app.sidehustle.offerManagement.dto.OfferDTO;
import pl.sidehustle.app.sidehustle.offerManagement.service.OffersService;
import pl.sidehustle.app.sidehustle.security.jwt.JwtUtils;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;
import pl.sidehustle.app.sidehustle.security.jwt.RefreshToken;
import pl.sidehustle.app.sidehustle.security.payload.JwtResponse;
import pl.sidehustle.app.sidehustle.security.payload.LoginRequest;
import pl.sidehustle.app.sidehustle.security.payload.MessageResponse;
import pl.sidehustle.app.sidehustle.security.service.UserDetailsImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offer")
@CrossOrigin(origins = "http://localhost:3000")
public class OffersController {
    Logger logger = LoggerFactory.getLogger(OffersService.class);

    private final OffersService offersService;

    private final CVService cvService;

    @Autowired
    public OffersController(OffersService offersService, CVService cvService) {
        this.offersService = offersService;
        this.cvService = cvService;
    }

    @GetMapping("/count")
    public Integer getOffersCount() {
        return offersService.getOffersCount();
    }

    @GetMapping("/list")
    public List<OfferDTO> getOffers(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer size) {
        logger.info("Received getOffers request");
        return offersService.getOfferList(offset, size);
    }


    @GetMapping("/{id}")
    public OfferDTO getOffer(@PathVariable Long id, Authentication authentication) {
        String userId = authentication.getName();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        logger.info("Received getOffer request from: {} with id {}", userDetails.getUser().getUsername(), userDetails.getUser().getId());
        return offersService.getOfferById(id);
    }

    @GetMapping("/cvs/{id}")
    public List<CVDTO> getCVs(@PathVariable Long id) {
        logger.info("Received getCVs request");
        return cvService.getCVsFromOffer(id);
    }

    @GetMapping("/list/user/{id}")
    public List<OfferDTO> getOffersByUserId(@PathVariable Long id) {
        logger.info("Received getOffersByUserId request");
        return offersService.getOwnersOffers(id);
    }

    @GetMapping("/list/location/{location_id}")
    public List<OfferDTO> getOffersByLocationId(@PathVariable Long location_id) {
        logger.info("Received getOffersByLocationId request");
        return offersService.getOffersByLocationId(location_id);
    }

    @PostMapping("/new")
    public ResponseEntity<?>  addOffer(@Valid @RequestBody NewOfferRequestDTO offerRequestDTO, Authentication authentication) {
        logger.info("Received addOffer request");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        Role role = userDetails.getRole();
//        offersService

        return ResponseEntity.ok(new MessageResponse("Offer added successfully"));
    }


}
