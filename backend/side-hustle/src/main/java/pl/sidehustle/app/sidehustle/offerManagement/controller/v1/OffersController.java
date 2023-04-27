package pl.sidehustle.app.sidehustle.offerManagement.controller.v1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sidehustle.app.sidehustle.offerManagement.dto.OfferDTO;
import pl.sidehustle.app.sidehustle.offerManagement.service.OffersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offer")
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

}
