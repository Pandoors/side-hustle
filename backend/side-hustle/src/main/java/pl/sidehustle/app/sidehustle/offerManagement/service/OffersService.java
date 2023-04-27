package pl.sidehustle.app.sidehustle.offerManagement.service;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.sidehustle.app.sidehustle.enums.JobType;
import pl.sidehustle.app.sidehustle.exceptions.BadRequestException;
import pl.sidehustle.app.sidehustle.offerManagement.dto.OfferDTO;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@NoArgsConstructor
public class OffersService {
    private int defaultSize = 10;
    private int maxSize = 10;

    Logger logger = LoggerFactory.getLogger(OffersService.class);


    /**
     * Dummy method for now.
     *
     * @param offset how many records to skip
     * @param size   how many records to fetch
     * @return list of offer DTOs fetched from db but there's no db right now
     */
    public List<OfferDTO> getOfferList(Integer offset, Integer size) {
        List<OfferDTO> offers = offerFactory();
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

    private List<OfferDTO> offerFactory() {
        OfferDTO offer1 = new OfferDTO(1L, "Koszenie trawy", "Prokocim", "12.04", "14.04", JobType.PHYSICAL.getType(), "4-5 h", "21zł/h", 50.00813486564785, 19.998598306804602);
        OfferDTO offer2 = new OfferDTO(2L, "Składanie mebli", "Rybitwy", "10.04", "11.04", JobType.PHYSICAL.getType(), "6-7 h", "11zł/h", 50.04520626239646, 20.043231912485453);
        OfferDTO offer3 = new OfferDTO(3L, "Uzupełnianie excela", "Stare Podgórze", "10.04", "10.04", JobType.MENTAL.getType(), "6-7 h", "40zł/h", 50.041623260747194, 19.944081545580133);
        OfferDTO offer4 = new OfferDTO(4L, "Wykładanie towaru", "Kazimierz", "13.05", "13.05", JobType.PHYSICAL.getType(), "2-3 h", "112zł/h", 50.04622992803206, 19.94455976278386);
        OfferDTO offer5 = new OfferDTO(5L, "Spacer z psem", "Mogiła", "13.03", "13.03", JobType.PHYSICAL.getType(), "0.5-1 h", "20zł/h", 50.05871689095981, 20.061722977696093);
        OfferDTO offer6 = new OfferDTO(6L, "Pomoc przy przewozie mebli", "Czarna wieś", "05.03", "05.03", JobType.PHYSICAL.getType(), "4-5 h", "40zł/h", 50.06608472152409, 19.904070706201946);
        OfferDTO offer7 = new OfferDTO(7L, "Pomoc przy przeprowadzce", "Dębniki", "05.03", "05.03", JobType.PHYSICAL.getType(), "5-6 h", "30zł/h", 50.047974186027126, 19.92553917491516);
        OfferDTO offer8 = new OfferDTO(8L, "Skoszenie trawnika", "Czyżyny", "05.03", "05.03", JobType.PHYSICAL.getType(), "2-3 h", "34zł/h", 50.06867125487128, 20.00656794881595);
        OfferDTO offer9 = new OfferDTO(9L, "Przeniesienie fortepianu", "Grzegórzki", "05.02", "05.02", JobType.PHYSICAL.getType(), "1-2 h", "64zł/h", 50.067277525799824, 19.97671217526877);
        OfferDTO offer10 = new OfferDTO(10L, "Zamontowanie koła do samochodu", "Grzegórzki", "05.02", "05.02", JobType.PHYSICAL.getType(), "1-2 h", "24zł/h", 50.05821729960025, 19.96422703360358);

        return new ArrayList<>(Arrays.asList(offer1, offer2, offer3, offer4, offer5, offer6, offer7, offer8, offer9, offer10));
    }

    public Integer getOffersCount() {
        return offerFactory().size();
    }

}
