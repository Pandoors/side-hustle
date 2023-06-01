package pl.sidehustle.app.sidehustle.offerManagement.controller.v1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sidehustle.app.sidehustle.offerManagement.dto.OfferDTO;
import pl.sidehustle.app.sidehustle.offerManagement.service.OffersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offer")
@CrossOrigin(origins = "http://localhost:3000")

public class OffersController {
    Logger logger = LoggerFactory.getLogger(OffersService.class);

    private final OffersService offersService;

    @Autowired
    public OffersController(OffersService offersService) {
        this.offersService = offersService;
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
    public OfferDTO getOffer(@PathVariable Long id) {
        logger.info("Received getOffer request");
        return offersService.getOfferById(id);
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

}
