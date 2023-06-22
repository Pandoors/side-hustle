package pl.sidehustle.app.sidehustle.accountManagement.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sidehustle.app.sidehustle.accountManagement.dto.CVDTO;
import pl.sidehustle.app.sidehustle.accountManagement.service.CVService;

@RestController
@RequestMapping("/api/v1/cv")
@CrossOrigin(origins = "http://localhost:3000")
public class CVController {
    Logger logger = LoggerFactory.getLogger(CVService.class);

    private final CVService cvService;

    @Autowired
    public CVController(CVService CVService) {
        this.cvService = CVService;
    }

    @GetMapping("/{id}")
    public CVDTO getCVByUserId(@PathVariable Long id) {
        return cvService.getCVByUserId(id);
    }
}
